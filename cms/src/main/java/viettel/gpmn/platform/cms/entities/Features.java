package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.Mapping;
import viettel.gpmn.platform.cms.enums.FeatureType;
import viettel.gpmn.platform.core.entities.EntityWithInfo;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "features")
public class Features extends EntityWithInfo {
    @Column
    @Enumerated(EnumType.ORDINAL)
    private FeatureType featureType;

    @Column
    private Integer seq;

    @Column
    private String url;

    @Column
    private String description;

    @Column
    String parentFeatureId;

    @OneToMany(mappedBy="parentFeatureId", fetch = FetchType.EAGER)
    Set<Features> childrenFeature = new HashSet<>();

}
