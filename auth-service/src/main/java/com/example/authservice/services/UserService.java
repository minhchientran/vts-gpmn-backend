package com.example.authservice.services;

import com.example.authservice.data.users.UserRegisterData;
import com.example.authservice.entities.Users;
import com.example.authservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.viettel.core.services.BaseService;

@Service
public class UserService extends BaseService {
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createUser(UserRegisterData userRegisterData) {
        Users user = new Users();
        user.setUsername(userRegisterData.getUsername());
        String encodePassword = bCryptPasswordEncoder.encode(userRegisterData.getPassword());
        user.setPassword(encodePassword);
        user.setSubsystem(1);
        userRepository.save(user);
    }
}
