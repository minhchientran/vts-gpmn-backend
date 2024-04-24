package com.example.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableMethodSecurity
@SpringBootApplication(
        scanBasePackages = {
                "vn.viettel.core.data",
                "vn.viettel.core.configs",
                "vn.viettel.core.services",
                "com.example.authservice.configs",
                "com.example.authservice.controllers",
                "com.example.authservice.repositories",
                "com.example.authservice.services",
        }
)
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}


