package viettel.gpmn.platform.retail.data.staff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.DBStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffData extends InfoData {
    private String positionName;
    private String unitName;

    public StaffData(
        String id,
        String code,
        String name,
        String positionName,
        String unitName,
        DBStatus status
    ) {
        this.setId(id);
        this.setCode(code);
        this.setName(name);
        this.positionName = positionName;
        this.unitName = unitName;
        setStatus(status);
    }
}
