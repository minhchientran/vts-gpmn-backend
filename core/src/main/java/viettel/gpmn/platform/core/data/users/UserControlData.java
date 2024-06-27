package viettel.gpmn.platform.core.data.users;

import lombok.*;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.ControlAttribute;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserControlData extends InfoData {
    private String id;
    private ControlAttribute controlAttribute;
}
