package vn.viettel.cms.controllers;

import vn.viettel.cms.data.login.LoginBody;
import vn.viettel.cms.data.login.LoginResponse;
import vn.viettel.cms.services.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;
import vn.viettel.core.services.JwtService;

@AllArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthenticationController extends BaseController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @SneakyThrows
    @PostMapping("/login")
    public Response authenticate(@RequestBody LoginBody loginBody) {
        Authentication authenticatedUser = authenticationService.authenticate(loginBody);
        String jwtToken = jwtService.generateToken(authenticationService.authentication2claims(authenticatedUser));
        return Response.ok(new LoginResponse(jwtToken));
    }

}
