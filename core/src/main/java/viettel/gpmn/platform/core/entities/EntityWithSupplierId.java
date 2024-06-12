package viettel.gpmn.platform.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Getter
@Setter
@MappedSuperclass
@FilterDef(name = "supplierFilter", parameters = {@ParamDef(name = "supplierId", type = String.class)})
@Filter(name = "supplierFilter", condition = "(:supplierId = 'VIETTEL_ADMIN' or supplier_id = :supplierId)")
public class EntityWithSupplierId {
    @Column
    private String supplierId;
}
