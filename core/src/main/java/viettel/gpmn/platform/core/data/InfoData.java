package viettel.gpmn.platform.core.data;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
@AllArgsConstructor
@MappedSuperclass
public class InfoData extends BaseData {
    private String code;
    private String name;
}
