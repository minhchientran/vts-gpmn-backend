package viettel.gpmn.platform.cms.data.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StaffRoleUpdateData {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
