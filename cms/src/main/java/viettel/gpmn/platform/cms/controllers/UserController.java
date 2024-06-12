package viettel.gpmn.platform.cms.controllers;

import viettel.gpmn.platform.cms.data.users.UserRegisterData;
import viettel.gpmn.platform.cms.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {

    private UserService userService;

    @PostMapping
    Response createUser(@RequestBody UserRegisterData userRegisterData) {
        userService.createUser(userRegisterData);
        return Response.ok();
    }

}
