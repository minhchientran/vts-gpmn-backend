package com.example.authservice.services;

import com.example.authservice.data.users.UserRegisterData;
import com.example.authservice.entities.Users;
import com.example.authservice.repositories.FeaturesRepository;
import com.example.authservice.repositories.UserRepository;
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
    FeaturesRepository featuresRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String loginIdentityString) throws UsernameNotFoundException {
        String[] loginIdentityStringSplit = loginIdentityString.split(Constant.SEPARATE);
        Subsystem subsystem = Subsystem.values()[Integer.parseInt(loginIdentityStringSplit[0])];
        String username = loginIdentityStringSplit[1];
        UserTokenData userTokenData = switch (subsystem) {
            case CMS -> userRepository.findByUsernameAndSubsystem(username, subsystem);
            case ADMIN, ECOMMERCE -> null;
        };
        assert userTokenData != null;
        List<UserFeatureData> listUserFeatureData = featuresRepository.getFeatures(username, userTokenData.getSupplierId());
        userTokenData.setAuthorities(listUserFeatureData);
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
