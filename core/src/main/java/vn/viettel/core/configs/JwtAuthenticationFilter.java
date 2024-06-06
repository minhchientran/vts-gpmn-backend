package vn.viettel.core.configs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import vn.viettel.core.configs.tenant.TenantContext;
import vn.viettel.core.data.users.Authority;
import vn.viettel.core.data.users.UserTokenData;
import vn.viettel.core.services.JwtService;
import vn.viettel.core.utilities.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;
    private final HandlerExceptionResolver handlerExceptionResolver;
    public JwtAuthenticationFilter(
            ObjectMapper objectMapper,
            JwtService jwtService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.objectMapper = objectMapper;
        this.jwtService = jwtService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

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
                }
            }
            filterChain.doFilter(request, response);
        }
        catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
