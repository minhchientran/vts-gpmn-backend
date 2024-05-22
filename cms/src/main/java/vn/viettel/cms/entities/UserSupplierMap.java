package vn.viettel.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithAware;
import vn.viettel.core.enums.Subsystem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_supplier_map")
public class UserSupplierMap extends EntityWithAware {
    @Column
    private String userId;
    @Column
    private String supplierId;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Subsystem subsystem;
}
