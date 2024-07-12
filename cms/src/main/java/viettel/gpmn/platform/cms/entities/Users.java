package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.*;
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
@Table(name = "users")
public class Users extends EntityWithAware {
    @Column
    private String username;
    @Column
    private String password;
}
