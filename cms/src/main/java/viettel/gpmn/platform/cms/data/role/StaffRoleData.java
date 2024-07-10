package viettel.gpmn.platform.cms.data.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.BaseData;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StaffRoleData extends BaseData {
    private String staffId;
    private String roleId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
