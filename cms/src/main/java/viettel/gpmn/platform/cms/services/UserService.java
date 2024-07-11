package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel.gpmn.platform.cms.data.suppliers.SupplierForUserData;
import viettel.gpmn.platform.cms.data.users.UserInfoData;
import viettel.gpmn.platform.cms.data.users.UserPasswordData;
import viettel.gpmn.platform.cms.data.users.UserRegisterData;
import viettel.gpmn.platform.cms.entities.Features;
import viettel.gpmn.platform.cms.entities.UserInfo;
import viettel.gpmn.platform.cms.entities.UserSupplierMap;
import viettel.gpmn.platform.cms.entities.Users;
import viettel.gpmn.platform.cms.repositories.*;
import viettel.gpmn.platform.core.configs.tenant.TenantContext;
import viettel.gpmn.platform.core.data.users.UserFeatureData;
import viettel.gpmn.platform.core.data.users.UserTokenData;
import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.enums.KafkaTopic;
import viettel.gpmn.platform.core.enums.OTPPrefix;
import viettel.gpmn.platform.core.enums.Subsystem;
import viettel.gpmn.platform.core.services.BaseService;
import viettel.gpmn.platform.core.services.KafkaService;
import viettel.gpmn.platform.core.services.MinIOService;
import viettel.gpmn.platform.core.utilities.Constant;
import viettel.gpmn.platform.core.utilities.MinIOPath;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService extends BaseService implements UserDetailsService {
    private FeatureRepository featuresRepository;
    private SupplierRepository supplierRepository;
    private UserRepository userRepository;
    private UserInfoRepository userInfoRepository;
    private UserSupplierMapRepository userSupplierMapRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RedisTemplate<String, Object> redisTemplate;
    private KafkaService kafkaService;
    private MinIOService minIOService;

    @Override
    public UserDetails loadUserByUsername(String loginIdentityString) throws UsernameNotFoundException {
        String[] loginIdentityStringSplit = loginIdentityString.split(Constant.SEPARATE);
        Subsystem subsystem = Subsystem.values()[Integer.parseInt(loginIdentityStringSplit[0])];
        String username = loginIdentityStringSplit[1];
        UserTokenData userTokenData = switch (subsystem) {
            case CMS -> userRepository.findByUsernameAndSubsystem(username, subsystem);
            case ADMIN, RETAIL_BUY, RETAIL_SELL, CONSUMER, PG  -> null;
        };
        List<UserFeatureData> listUserFeatureData = switch (subsystem) {
            case CMS -> {
                List<Features> listFeature = featuresRepository
                        .getAdminFeatures(userTokenData.getUserId(), userTokenData.getSupplierId());
                yield this.modelMapper.map(listFeature, new TypeToken<List<UserFeatureData>>() {}.getType());
            }
            case ADMIN, RETAIL_BUY, RETAIL_SELL, CONSUMER, PG -> null;
        };
        assert userTokenData != null;
        userTokenData.setAuthorities(listUserFeatureData);
        return userTokenData;
    }

    public Boolean checkUserExisted(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public void createUser(UserRegisterData userRegisterData) {
        if (userRegisterData.getUserData() != null) {
            UserInfoData userInfoData = userRegisterData.getUserData();
            if (redisTemplate.opsForValue().get(OTPPrefix.CREATE_ACCOUNT_.getValue() + userInfoData.getUsername()) != null) {
                Users user = new Users();
                user.setUsername(userInfoData.getUsername());
                String encodePassword = bCryptPasswordEncoder.encode(userInfoData.getPassword());
                user.setPassword(encodePassword);
                user = userRepository.save(user);

                List<String> listSupplierId = userRegisterData.getListSupplierId();
                if (listSupplierId != null && !listSupplierId.isEmpty()) {
                    List<UserSupplierMap> listUserSupplierMap = new ArrayList<>();
                    for (String supplierId : listSupplierId) {
                        UserSupplierMap userSupplierMap = new UserSupplierMap();
                        userSupplierMap.setUserId(user.getId());
                        userSupplierMap.setSupplierId(supplierId);
                        userSupplierMap.setSubsystem(Subsystem.RETAIL_SELL);
                        userSupplierMap.setStatus(DBStatus.DRAFT);
                        listUserSupplierMap.add(userSupplierMap);
                    }
                    userSupplierMapRepository.saveAll(listUserSupplierMap);
                }

                UserInfo userInfo = modelMapper.map(userInfoData, UserInfo.class);
                String imageCardFrontUrl = null;
                if (userInfoData.getImageCardFrontData() != null && userInfoData.getImageCardFrontFileName() != null) {
                    imageCardFrontUrl = MinIOPath.USER_CARD + userInfoData.getImageCardBackFileName();
                    minIOService.WriteToMinIO(
                            new ByteArrayInputStream(Base64.getDecoder().decode(userInfoData.getImageCardBackData())),
                            MinIOPath.MASTER_BUCKET,
                            imageCardFrontUrl
                    );
                }
                userInfo.setImageCardFront(imageCardFrontUrl);
                String imageCardBackUrl = null;
                if (userInfoData.getImageCardBackData() != null && userInfoData.getImageCardBackFileName() != null) {
                    imageCardBackUrl = MinIOPath.USER_CARD + userInfoData.getImageCardBackFileName();
                    minIOService.WriteToMinIO(
                            new ByteArrayInputStream(Base64.getDecoder().decode(userInfoData.getImageCardBackData())),
                            MinIOPath.MASTER_BUCKET,
                            imageCardBackUrl
                    );
                }
                userInfo.setImageCardBack(imageCardBackUrl);
                userInfoRepository.save(userInfo);

                kafkaService.send(KafkaTopic.BROADCAST_NEW_RETAILER_INFO, userRegisterData.getRetailerData());
                redisTemplate.delete(OTPPrefix.CREATE_ACCOUNT_.getValue() + userInfoData.getUsername());
            }
        }

    }

    @Transactional
    @SneakyThrows
    public void changePassword(UserPasswordData userPasswordData) {
        UserTokenData userTokenData = TenantContext.getUserInfo();
        if (userTokenData != null) {
            Users user = userRepository.findById(userTokenData.getUserId()).orElseThrow();
            if (bCryptPasswordEncoder.matches(userPasswordData.getCurrentPassword(), user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(userPasswordData.getNewPassword()));
                userRepository.save(user);
            }
            // TODO
            throw new Exception();
        }
        // TODO
        throw new Exception();
    }

    @SneakyThrows
    public Page<SupplierForUserData> getListSupplierForUser(String name, Boolean isRegistered, Pageable pageable) {
        UserTokenData UserTokenData = TenantContext.getUserInfo();
        if (UserTokenData != null) {
            return supplierRepository.getListSupplierForUser(UserTokenData.getUserId(), name, isRegistered, pageable);
        }
        // TODO
        throw new Exception();
    }

    @SneakyThrows
    public UserInfoData getUserInfo() {
        UserTokenData UserTokenData = TenantContext.getUserInfo();
        if (UserTokenData != null) {
            UserInfo userInfo = userInfoRepository.findById(UserTokenData.getUserId()).orElseThrow();
            return modelMapper.map(userInfo, UserInfoData.class);
        }
        // TODO
        throw new Exception();
    }
}
