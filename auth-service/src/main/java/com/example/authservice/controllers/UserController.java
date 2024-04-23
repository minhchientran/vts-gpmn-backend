package com.example.authservice.controllers;

import com.example.authservice.data.users.UserRegisterData;
import com.example.authservice.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.data.response.Response;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping
    Response createUser(@RequestBody UserRegisterData userRegisterData) {
        userService.createUser(userRegisterData);
        return Response.ok();
    }
}
