package vn.viettel.core.data;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.viettel.core.enums.DBStatus;

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
