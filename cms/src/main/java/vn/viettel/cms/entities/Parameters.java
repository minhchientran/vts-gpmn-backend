package vn.viettel.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithAware;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parameters")
public class Parameters extends EntityWithAware {

    @Column
    private String code;

    @Column
    private String value;

    @Column
    private String description;
}
