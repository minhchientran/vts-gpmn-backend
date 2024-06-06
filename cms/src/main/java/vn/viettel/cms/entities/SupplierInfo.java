package vn.viettel.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithAware;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_info")
public class SupplierInfo extends EntityWithAware {
    @Column
    private String supplierId;
    @Column
    private String representationName;
    @Column
    private String email;
    @Column
    private String logo;
    @Column
    private Float rating;

}
