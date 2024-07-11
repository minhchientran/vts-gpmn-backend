package viettel.gpmn.platform.cms.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.users.UserPasswordData;
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

    @PutMapping
    Response changePassword(@RequestBody UserPasswordData userPasswordData) {
        userService.changePassword(userPasswordData);
        return Response.ok();
    }

    @GetMapping(value = {"/check"})
    Response checkUserExisted(@RequestParam String username) {
        return Response.ok(userService.checkUserExisted(username));
    }

    @GetMapping(value = {"/suppliers"})
    Response getListSupplierForUser(
            @RequestParam String name,
            @RequestParam Boolean isRegistered,
            Pageable pageable) {
        return Response.ok(userService.getListSupplierForUser(name, isRegistered, pageable));
    }

    @GetMapping
    Response getUserInfo() {
        return Response.ok(userService.getUserInfo());
    }
}
