package vn.viettel.cms.data.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.viettel.cms.data.modules.ModuleAddFeatureData;
import vn.viettel.core.enums.DBStatus;
import vn.viettel.core.enums.FeatureType;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FeatureModuleData extends ModuleAddFeatureData {

    public FeatureModuleData(
            String featureCode,
            String featureName,
            String parentFeatureCode,
            FeatureType featureType,
            String url,
            Integer seq,
            String moduleId,
            String featureId,
            DBStatus status
    ) {
        this.featureCode = featureCode;
        this.featureName = featureName;
        this.parentFeatureCode = parentFeatureCode;
        this.featureType = featureType;
        this.url = url;
        this.seq = seq;
        this.setModuleId(moduleId);
        this.setFeatureId(featureId);
        this.setStatus(status);
    }

    private String featureCode;
    private String featureName;
    private String parentFeatureCode;
    private FeatureType featureType;
    private String url;
    private Integer seq;
}

