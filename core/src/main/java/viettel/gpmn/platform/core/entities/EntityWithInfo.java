package viettel.gpmn.platform.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class EntityWithInfo extends EntityWithAware {
    @Column
    private String code;
    @Column
    private String name;
}
