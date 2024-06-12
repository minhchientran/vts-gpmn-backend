package viettel.gpmn.platform.core.configs.jpa;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.domain.AuditorAware;
import viettel.gpmn.platform.core.configs.tenant.TenantContext;
import viettel.gpmn.platform.core.data.users.UserTokenData;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        UserTokenData userTokenData = TenantContext.getUserInfo();
        if (userTokenData != null) {
            return Optional.of(userTokenData.getUsername());
        }
        return Optional.of("SYSTEM");
    }

}
