package vn.viettel.cms.services;

import vn.viettel.cms.data.login.LoginBody;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.viettel.core.data.users.UserTokenData;
import vn.viettel.core.services.BaseService;
import vn.viettel.core.utilities.Constant;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationService extends BaseService {

    private final AuthenticationManager authenticationManager;

    public Authentication authenticate(LoginBody loginBody) {
        String loginIdentityString = String.join(Constant.SEPARATE, loginBody.subsystem().toString(), loginBody.username());
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginIdentityString,
                        loginBody.password()
                )
        );
    }

    public Map<String, Object> authentication2claims(Authentication authentication) {
        UserTokenData userToken = (UserTokenData) authentication.getPrincipal();
        Map<String, Object> mapUserTokenData = this.objectMapper.convertValue(userToken, new TypeReference<>() {});
        mapUserTokenData.remove(Constant.PASSWORD);
        return mapUserTokenData;
    }
}
