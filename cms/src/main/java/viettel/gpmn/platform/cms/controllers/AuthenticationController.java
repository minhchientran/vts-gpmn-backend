package viettel.gpmn.platform.cms.controllers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.login.LoginBody;
import viettel.gpmn.platform.cms.data.login.LoginResponse;
import viettel.gpmn.platform.cms.services.AuthenticationService;
import viettel.gpmn.platform.cms.services.OTPService;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;
import viettel.gpmn.platform.core.services.JwtService;

@AllArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthenticationController extends BaseController {

    private JwtService jwtService;
    private AuthenticationService authenticationService;
    private OTPService otpService;

    @SneakyThrows
    @PostMapping("/login")
    public Response authenticate(@RequestBody LoginBody loginBody) {
        Authentication authenticatedUser = authenticationService.authenticate(loginBody);
        String jwtToken = jwtService.generateToken(authenticationService.authentication2claims(authenticatedUser));
        return Response.ok(new LoginResponse(jwtToken));
    }

    @PostMapping("/otp")
    public Response sendOtp(
            @RequestParam String prefix,
            @RequestParam String objectId
    ) {
        return Response.ok(otpService.generateOTP(prefix, objectId));
    }

}
