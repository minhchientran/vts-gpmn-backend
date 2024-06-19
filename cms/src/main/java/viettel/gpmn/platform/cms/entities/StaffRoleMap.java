package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.entities.EntityWithSupplierId;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff_role_map")
public class StaffRoleMap extends EntityWithSupplierId {
    @Column
    private String staffId;
    @Column
    private String roleId;
    @Column
    private LocalDateTime fromDate;
    @Column
    private LocalDateTime toDate;
}
