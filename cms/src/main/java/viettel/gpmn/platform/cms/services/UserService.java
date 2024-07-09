package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.cms.data.users.UserRegisterData;
import viettel.gpmn.platform.cms.entities.Features;
import viettel.gpmn.platform.cms.entities.Users;
import viettel.gpmn.platform.cms.repositories.FeatureRepository;
import viettel.gpmn.platform.cms.repositories.UserRepository;
import viettel.gpmn.platform.core.data.users.UserFeatureData;
import viettel.gpmn.platform.core.data.users.UserTokenData;
import viettel.gpmn.platform.core.enums.OTPPrefix;
import viettel.gpmn.platform.core.enums.Subsystem;
import viettel.gpmn.platform.core.services.BaseService;
import viettel.gpmn.platform.core.utilities.Constant;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService extends BaseService implements UserDetailsService {
    private UserRepository userRepository;
    private FeatureRepository featuresRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RedisTemplate<String, Object> redisTemplate;

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
    public void createUser(UserRegisterData userRegisterData) {
        if (redisTemplate.opsForValue().get(OTPPrefix.CREATE_ACCOUNT_.getValue() + userRegisterData.getUsername()) != null) {
            Users user = new Users();
            user.setUsername(userRegisterData.getUsername());
            String encodePassword = bCryptPasswordEncoder.encode(userRegisterData.getPassword());
            user.setPassword(encodePassword);
            userRepository.save(user);
            redisTemplate.delete(OTPPrefix.CREATE_ACCOUNT_.getValue() + userRegisterData.getUsername());
        }
    }

    public Boolean checkUserExisted(String username) {
        return userRepository.existsByUsername(username);
    }

}
