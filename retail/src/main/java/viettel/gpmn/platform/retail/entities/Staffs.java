package viettel.gpmn.platform.retail.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.entities.EntityWithInfo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staffs")
public class Staffs extends EntityWithInfo {
    @Column
    private String positionId;
    @Column
    private String supplierUnitId;
    @Column
    private String userId;
}
