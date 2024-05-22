package vn.viettel.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithAware;
import vn.viettel.core.enums.Subsystem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "module_feature_map")
public class ModuleFeatureMap extends EntityWithAware {
    @Column
    private String moduleId;

    @Column
    private String featureId;

    @Column
    @Enumerated(value = EnumType.ORDINAL)
    private Subsystem subsystem;
}
