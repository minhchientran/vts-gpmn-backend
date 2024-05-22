package vn.viettel.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import vn.viettel.core.entities.EntityWithAware;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_database_info")
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
