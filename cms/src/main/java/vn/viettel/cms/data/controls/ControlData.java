package vn.viettel.cms.data.controls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.viettel.core.data.InfoData;
import vn.viettel.core.enums.ControlType;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ControlData extends InfoData {
    private ControlType controlType;
    private String description;
}
