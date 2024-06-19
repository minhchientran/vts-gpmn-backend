package viettel.gpmn.platform.core.configs.tenant;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import viettel.gpmn.platform.core.utilities.Constant;

public class TenantListener {
    @PreUpdate
    @PreRemove
    @PrePersist
    public void setSupplierId(Object entity) {
        final String supplierId = TenantContext.getSupplierId();
        if (supplierId != null) {
            ((TenantAware) entity).setSupplierId(supplierId);
        }
        else {
            ((TenantAware) entity).setSupplierId(Constant.DEFAULT_USER);
        }
    }
}