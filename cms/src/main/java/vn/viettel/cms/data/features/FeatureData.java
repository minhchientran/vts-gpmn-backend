package vn.viettel.cms.data.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.viettel.core.data.InfoData;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FeatureData extends InfoData {
    private String parentFeatureId;
    private String parentFeatureCode;
    private String type;
    private String description;
    private String url;
    private Integer seq;
}