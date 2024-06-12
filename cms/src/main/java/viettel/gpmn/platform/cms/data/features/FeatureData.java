package viettel.gpmn.platform.cms.data.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import viettel.gpmn.platform.cms.enums.FeatureType;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.DBStatus;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FeatureData extends InfoData {
    private String parentFeatureId;
    private String parentFeatureCode;
    private FeatureType featureType;
    private String description;
    private String url;
    private Integer seq;

    public FeatureData(
        String id,
        String code,
        String name,
        String parentFeatureCode,
        FeatureType featureType,
        String description,
        String url,
        Integer seq,
        DBStatus status
    ) {
        setId(id);
        setCode(code);
        setName(name);
        this.parentFeatureCode = parentFeatureCode;
        this.featureType = featureType;
        this.description = description;
        this.url = url;
        this.seq = seq;
        setStatus(status);
    }
}