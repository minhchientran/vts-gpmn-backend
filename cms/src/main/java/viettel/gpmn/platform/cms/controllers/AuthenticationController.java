package viettel.gpmn.platform.cms.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import viettel.gpmn.platform.cms.data.login.LoginBody;
import viettel.gpmn.platform.cms.data.login.LoginResponse;
import viettel.gpmn.platform.cms.services.AuthenticationService;
import viettel.gpmn.platform.cms.services.FeatureService;
import viettel.gpmn.platform.cms.services.OTPService;
import viettel.gpmn.platform.core.configs.tenant.TenantContext;
import viettel.gpmn.platform.core.controllers.BaseController;
import viettel.gpmn.platform.core.data.response.Response;
import viettel.gpmn.platform.core.services.JwtService;

import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController extends BaseController {

    private AuthenticationService authenticationService;
    private JwtService jwtService;
    private OTPService otpService;

    @SneakyThrows
    @PostMapping("/login")
    public Response authenticate(@RequestBody @Valid LoginBody loginBody) {
        Authentication authenticatedUser = authenticationService.authenticate(loginBody);
        String jwtToken = jwtService.generateToken(authenticationService.authentication2claims(authenticatedUser));
        return Response.ok(new LoginResponse(jwtToken));
    }

    @GetMapping(value = {"/features"})
    public Response getUserFeature() {
        return Response.ok(Objects.requireNonNull(TenantContext.getUserInfo()).getAuthorities());
    }

    @PostMapping("/otp")
    public Response sendOtp(
            @RequestParam @NotBlank String prefix,
            @RequestParam @NotBlank String objectId
    ) {
        return Response.ok(otpService.generateOTP(prefix, objectId));
    }

}
