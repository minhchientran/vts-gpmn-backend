package com.example.authservice.controllers;

import com.example.authservice.data.login.LoginBody;
import com.example.authservice.data.login.LoginResponse;
import com.example.authservice.services.AuthenticationService;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;
import vn.viettel.core.services.JwtService;

@RequestMapping("/api/v1/authentication")
@RestController
public class AuthenticationController extends BaseController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;


    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @SneakyThrows
    @PostMapping("/login")
    public Response authenticate(@RequestBody LoginBody loginBody) {
        Authentication authenticatedUser = authenticationService.authenticate(loginBody);
        String jwtToken = jwtService.generateToken(authenticationService.authentication2claims(authenticatedUser));
        return new Response(new LoginResponse(jwtToken, jwtService.getExpirationTime()));
    }

}
