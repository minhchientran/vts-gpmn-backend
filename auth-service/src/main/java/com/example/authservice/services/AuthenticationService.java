package com.example.authservice.services;

import com.example.authservice.data.login.LoginBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.viettel.core.utilities.Constant;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
    }


    public Authentication authenticate(LoginBody input) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()
                )
        );
    }

    public Map<String, Object> authentication2claims(Authentication authentication) throws IllegalAccessException {
        return new HashMap<>() {{
            Object principal = authentication.getPrincipal();
            if (principal != null) {
                for (Field field : principal.getClass().getDeclaredFields()) {
                    if (!Constant.PASSWORD.equals(field.getName())) {
                        field.setAccessible(true);
                        put(field.getName(), field.get(principal));
                    }
                }
            }
            put(Constant.AUTHORITIES, authentication.getAuthorities());
        }};
    }
}
