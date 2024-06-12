package viettel.gpmn.platform.core.data;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import viettel.gpmn.platform.core.enums.DBStatus;

@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
@AllArgsConstructor
@MappedSuperclass
public class BaseData {
    private String id;
    private DBStatus status = DBStatus.ACTIVE;
}
