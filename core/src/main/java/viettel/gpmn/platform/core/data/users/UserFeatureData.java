package viettel.gpmn.platform.core.data.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserFeatureData extends UserControlData {

    public UserFeatureData(String featureId, String featureCode, String controlId, String controlCode) {
        this.featureId = featureId;
        this.featureCode = featureCode;
        this.setControlId(controlId);
        this.setControlCode(controlCode);
    }

    private String featureId;
    private String featureCode;
}
