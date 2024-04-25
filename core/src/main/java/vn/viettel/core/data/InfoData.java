package vn.viettel.core.data;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class InfoData extends BaseData {
    private String code;
    private String name;
}
