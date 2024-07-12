package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.entities.EntityWithInfo;
import viettel.gpmn.platform.core.enums.Subsystem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "modules")
public class Modules extends EntityWithInfo {
    @Column
    private String description;
    @Column @Enumerated(EnumType.ORDINAL)
    private Subsystem subsystem;
}
