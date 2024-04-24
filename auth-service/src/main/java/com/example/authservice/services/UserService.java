package com.example.authservice.services;

import com.example.authservice.data.users.UserRegisterData;
import com.example.authservice.entities.Users;
import com.example.authservice.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.viettel.core.enums.Subsystem;
import vn.viettel.core.services.BaseService;
import vn.viettel.core.utilities.Constant;

@Service
public class UserService extends BaseService implements UserDetailsService {
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String loginIdentityString) throws UsernameNotFoundException {
        String[] loginIdentityStringSplit = loginIdentityString.split(Constant.SEPARATE);
        Subsystem subsystem = Subsystem.values()[Integer.parseInt(loginIdentityStringSplit[0])];
        String username = loginIdentityStringSplit[1];
        return switch (subsystem) {
            case ADMIN -> userRepository.findByUsernameAndSubsystem(username, subsystem);
            case ECOMMERCE -> null;
        };
    }
    public void createUser(UserRegisterData userRegisterData) {
        Users user = new Users();
        user.setUsername(userRegisterData.getUsername());
        String encodePassword = bCryptPasswordEncoder.encode(userRegisterData.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
    }
}
