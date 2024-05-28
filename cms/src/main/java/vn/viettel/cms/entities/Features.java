package vn.viettel.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithInfo;
import vn.viettel.core.enums.FeatureType;
import vn.viettel.core.enums.Subsystem;

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
