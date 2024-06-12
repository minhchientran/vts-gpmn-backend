package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.cms.enums.FeatureType;
import viettel.gpmn.platform.core.entities.EntityWithInfo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "features")
public class Features extends EntityWithInfo {
    @Column
    private String parentFeatureId;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private FeatureType featureType;

    @Column
    private Integer seq;

    @Column
    private String url;

    @Column
    private String description;

}
