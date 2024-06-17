package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import viettel.gpmn.platform.core.entities.EntityWithAware;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_database")
public class SupplierDatabase extends EntityWithAware {
    @Column
    private String url;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String supplierId;
}
