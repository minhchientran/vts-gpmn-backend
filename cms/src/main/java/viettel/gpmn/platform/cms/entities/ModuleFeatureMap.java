package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.entities.EntityWithAware;

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

}
