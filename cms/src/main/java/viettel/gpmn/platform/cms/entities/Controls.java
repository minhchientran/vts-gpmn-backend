package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.cms.enums.ControlType;
import viettel.gpmn.platform.core.entities.EntityWithInfo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "controls")
public class Controls extends EntityWithInfo {

    @Column
    @Enumerated(EnumType.ORDINAL)
    private ControlType controlType;

    @Column
    private String description;

    @Column
    private String featureId;

}
