package vn.viettel.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithAware;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_module_map")
public class SupplierModuleMap extends EntityWithAware {
    @Column
    private String supplierId;
    @Column
    private String moduleId;
    @Column
    private LocalDateTime activeDate;
    @Column
    private Integer activeTime;
    @Column
    private Integer numberAccount;
}
