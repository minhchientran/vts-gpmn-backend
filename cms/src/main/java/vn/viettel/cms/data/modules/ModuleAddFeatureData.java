package vn.viettel.cms.data.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.viettel.core.enums.DBStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleAddFeatureData {
    private String moduleId;
    private String featureId;
    private DBStatus status = DBStatus.ACTIVE;
}
