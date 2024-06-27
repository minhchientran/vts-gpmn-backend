package viettel.gpmn.platform.cms.data.controls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import viettel.gpmn.platform.cms.enums.ControlType;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.ControlAttribute;

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
