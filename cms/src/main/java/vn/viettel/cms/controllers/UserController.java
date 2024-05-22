package vn.viettel.cms.controllers;

import vn.viettel.cms.data.users.UserRegisterData;
import vn.viettel.cms.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {

    UserService userService;

    @PostMapping
    Response createUser(@RequestBody UserRegisterData userRegisterData) {
        userService.createUser(userRegisterData);
        return Response.ok();
    }

}
