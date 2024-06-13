package viettel.gpmn.platform.core.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import viettel.gpmn.platform.core.configs.tenant.MultiTenantManager;
import viettel.gpmn.platform.core.configs.tenant.TenantContext;
import viettel.gpmn.platform.core.data.users.UserTokenData;
import viettel.gpmn.platform.core.services.JwtService;
import viettel.gpmn.platform.core.utilities.Constant;

import java.util.HashMap;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final JwtService jwtService;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final MultiTenantManager multiTenantManager;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) {
        try {
            final String authHeader = request.getHeader(Constant.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith(Constant.BEARER)) {
                final String jwt = authHeader.substring(Constant.BEARER.length());
                if (jwtService.isTokenValid(jwt)) {
                    Claims claims = jwtService.extractAllClaims(jwt);
                    UserTokenData userTokenData =
                            objectMapper.convertValue(new HashMap<>(claims), UserTokenData.class);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userTokenData.getUsername(), null, null
                    );
                    authToken.setDetails(userTokenData);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    TenantContext.setUserInfo(userTokenData);
                    multiTenantManager.setCurrentTenant(userTokenData.getSupplierId());
                }
            }
            filterChain.doFilter(request, response);
        }
        catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
