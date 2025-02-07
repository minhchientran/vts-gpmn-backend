package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.*;
import lombok.*;
import viettel.gpmn.platform.core.entities.EntityWithAware;
import viettel.gpmn.platform.core.enums.Subsystem;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_supplier_map")
public class UserSupplierMap extends EntityWithAware {
    @Column
    private String userId;
    @Column
    private String supplierId;
    @Column @Enumerated(EnumType.ORDINAL)
    private Subsystem subsystem;
}
