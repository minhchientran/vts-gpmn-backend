package vn.viettel.cms.services;

import vn.viettel.cms.data.users.UserRegisterData;
import vn.viettel.cms.entities.Users;
import vn.viettel.cms.repositories.FeatureRepository;
import vn.viettel.cms.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.viettel.core.data.users.UserFeatureData;
import vn.viettel.core.data.users.UserTokenData;
import vn.viettel.core.enums.Subsystem;
import vn.viettel.core.services.BaseService;
import vn.viettel.core.utilities.Constant;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService extends BaseService implements UserDetailsService {
    UserRepository userRepository;
    FeatureRepository featuresRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

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
