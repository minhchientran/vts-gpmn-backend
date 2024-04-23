package vn.viettel.core.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import vn.viettel.core.configs.jpa.ULIDGenerator;

@Getter
@Setter
@MappedSuperclass
public class EntityWithULID {
    @Id
    @GeneratedValue(generator="ULIDGenerator")
    @GenericGenerator(name="ULIDGenerator", type = ULIDGenerator.class)
    private String id;
}
