package vn.viettel.cms.data.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.viettel.cms.data.modules.ModuleAddFeatureData;
import vn.viettel.core.enums.DBStatus;
import vn.viettel.cms.enums.FeatureType;

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

