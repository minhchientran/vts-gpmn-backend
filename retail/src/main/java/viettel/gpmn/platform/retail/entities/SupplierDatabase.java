package viettel.gpmn.platform.retail.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.entities.EntityWithAware;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_database")
public class SupplierDatabase extends EntityWithAware {
    @Column
    private String supplierId;
    @Column
    private String url;
    @Column
    private String username;
    @Column
    private String password;
}

