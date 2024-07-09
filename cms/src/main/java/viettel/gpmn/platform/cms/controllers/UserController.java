package viettel.gpmn.platform.cms.controllers;

import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.users.UserRegisterData;
import viettel.gpmn.platform.cms.services.UserService;
import lombok.AllArgsConstructor;
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

    @GetMapping(value = {"/check"})
    Response checkUserExisted(@RequestParam String username) {
        return Response.ok(userService.checkUserExisted(username));
    }
}
