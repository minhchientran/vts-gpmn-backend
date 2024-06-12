package viettel.gpmn.platform.cms.services;

import viettel.gpmn.platform.cms.entities.Users;
import viettel.gpmn.platform.cms.repositories.UserRepository;
import viettel.gpmn.platform.cms.data.users.UserRegisterData;
import viettel.gpmn.platform.cms.repositories.FeatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.core.data.users.UserFeatureData;
import viettel.gpmn.platform.core.data.users.UserTokenData;
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
            case CMS -> featuresRepository.getAdminFeatures(userTokenData.getUserId(), userTokenData.getSupplierId());
            case ADMIN, RETAIL_BUY, RETAIL_SELL, CONSUMER, PG -> null;
        };
        assert userTokenData != null;
        userTokenData.setAuthoritiesFromListFeature(listUserFeatureData);
        return userTokenData;
    }
    public void createUser(UserRegisterData userRegisterData) {
        Users user = new Users();
        user.setUsername(userRegisterData.getUsername());
        String encodePassword = bCryptPasswordEncoder.encode(userRegisterData.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
    }
}
