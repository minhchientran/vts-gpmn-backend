package viettel.gpmn.platform.cms.data.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.DBStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RoleData extends InfoData {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private DBStatus status;

    public RoleData(
        String id,
        String code,
        String name,
        DBStatus status,
        LocalDateTime fromDate,
        LocalDateTime toDate
    ) {
        setId(id);
        setCode(code);
        setName(name);
        this.status = status;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
