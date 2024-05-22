package vn.viettel.cms.data.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.viettel.core.enums.DBStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureAddControlData {
    private String featureId;
    private String controlId;
    private DBStatus dbStatus = DBStatus.ACTIVE;
}
