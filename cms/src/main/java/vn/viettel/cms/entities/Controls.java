package vn.viettel.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithInfo;
import vn.viettel.cms.enums.ControlAttribute;
import vn.viettel.cms.enums.ControlType;

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

    @Column
    @Enumerated(EnumType.ORDINAL)
    private ControlAttribute controlAttribute;
}
