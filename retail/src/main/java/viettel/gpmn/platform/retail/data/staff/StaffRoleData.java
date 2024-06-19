package viettel.gpmn.platform.retail.data.staff;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.DBStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StaffRoleData extends InfoData {
    private String staffId;
    private String roleId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public StaffRoleData(
            String id,
            String roleCode,
            String roleName,
            String staffId,
            String roleId,
            LocalDateTime fromDate,
            LocalDateTime toDate,
            DBStatus status
    ) {
        setId(id);
        setCode(roleCode);
        setName(roleName);
        this.staffId = staffId;
        this.roleId = roleId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        setStatus(status);
    }
}
