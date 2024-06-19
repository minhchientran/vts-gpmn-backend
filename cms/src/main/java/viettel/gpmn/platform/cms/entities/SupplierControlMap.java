package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.cms.enums.ControlAttribute;
import viettel.gpmn.platform.core.entities.EntityWithSupplierId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_control_map")
public class SupplierControlMap extends EntityWithSupplierId {
    @Column
    private String controlId;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private ControlAttribute controlAttribute = ControlAttribute.ENABLE;
}
