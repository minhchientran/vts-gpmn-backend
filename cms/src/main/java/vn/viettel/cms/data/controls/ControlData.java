package vn.viettel.cms.data.controls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.viettel.core.data.InfoData;
import vn.viettel.cms.enums.ControlAttribute;
import vn.viettel.cms.enums.ControlType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ControlData extends InfoData {
    private String featureId;
    private ControlType controlType;
    private String description;
    private ControlAttribute controlAttribute;
}
