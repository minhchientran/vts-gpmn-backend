package viettel.gpmn.platform.cms.data.role;

import lombok.*;
import viettel.gpmn.platform.cms.enums.FeatureType;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.DBStatus;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RoleFeatureData extends InfoData {
    public RoleFeatureData (
        String id,
        String code,
        String name,
        FeatureType featureType,
        String url,
        Integer seq,
        String parentFeatureId,
        String parentFeatureCode,
        String moduleId,
        String moduleCode,
        DBStatus status
    ) {
        setId(id);
        setCode(code);
        setName(name);
        this.moduleId = moduleId;
        this.moduleCode = moduleCode;
        this.parentFeatureId = parentFeatureId;
        this.parentFeatureCode = parentFeatureCode;
        this.featureType = featureType;
        this.url = url;
        this.seq = seq;
        this.setStatus(status);
    }

    private String moduleId;
    private String moduleCode;
    private String parentFeatureId;
    private String parentFeatureCode;
    private FeatureType featureType;
    private String url;
    private Integer seq;
    private DBStatus status;
}
