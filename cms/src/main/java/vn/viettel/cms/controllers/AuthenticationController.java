package vn.viettel.cms.controllers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.viettel.cms.data.login.LoginBody;
import vn.viettel.cms.data.login.LoginResponse;
import vn.viettel.cms.services.AuthenticationService;
import vn.viettel.cms.services.OTPService;
import vn.viettel.core.controllers.BaseController;
import vn.viettel.core.data.response.Response;
import vn.viettel.core.services.JwtService;

@AllArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthenticationController extends BaseController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final OTPService otpService;

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
