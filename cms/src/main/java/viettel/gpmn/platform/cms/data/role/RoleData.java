package viettel.gpmn.platform.cms.data.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.InfoData;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RoleData extends InfoData {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public RoleData(
        String id,
        String code,
        String name,
        LocalDateTime fromDate,
        LocalDateTime toDate
    ) {
        setId(id);
        setCode(code);
        setName(name);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
