package viettel.gpmn.platform.cms.data.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.BaseData;

@Getter
@Setter
@NoArgsConstructor
public class RoleFeatureMapData extends BaseData {
    private String roleId;
    private String featureId;
}
