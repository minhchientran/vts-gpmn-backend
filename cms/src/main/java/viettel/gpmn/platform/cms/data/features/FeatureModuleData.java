package viettel.gpmn.platform.cms.data.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import viettel.gpmn.platform.cms.data.modules.ModuleAddFeatureData;
import viettel.gpmn.platform.cms.enums.FeatureType;
import viettel.gpmn.platform.core.enums.DBStatus;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FeatureModuleData extends ModuleAddFeatureData {

    public FeatureModuleData(
            String code,
            String name,
            String parentFeatureCode,
            FeatureType featureType,
            String url,
            Integer seq,
            String moduleId,
            String featureId,
            DBStatus status
    ) {
        this.code = code;
        this.name = name;
        this.parentFeatureCode = parentFeatureCode;
        this.featureType = featureType;
        this.url = url;
        this.seq = seq;
        this.setModuleId(moduleId);
        this.setFeatureId(featureId);
        this.setStatus(status);
    }

    private String code;
    private String name;
    private String parentFeatureCode;
    private FeatureType featureType;
    private String url;
    private Integer seq;
}

