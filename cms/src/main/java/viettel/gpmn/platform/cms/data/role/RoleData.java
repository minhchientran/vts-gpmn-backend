package viettel.gpmn.platform.cms.data.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.InfoData;

@Getter
@Setter
@NoArgsConstructor
public class RoleData extends InfoData {
    private String fromDate;
    private String toDate;
}
