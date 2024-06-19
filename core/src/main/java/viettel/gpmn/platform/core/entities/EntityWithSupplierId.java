package viettel.gpmn.platform.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import viettel.gpmn.platform.core.configs.tenant.TenantAware;
import viettel.gpmn.platform.core.configs.tenant.TenantListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({TenantListener.class })
@FilterDef(name = "supplierFilter", parameters = {@ParamDef(name = "supplierId", type = String.class)})
@Filter(name = "supplierFilter", condition = "(:supplierId = 'VIETTEL_ADMIN' or supplier_id = :supplierId)")
public class EntityWithSupplierId extends EntityWithAware implements TenantAware {
    @Column
    private String supplierId;
}
