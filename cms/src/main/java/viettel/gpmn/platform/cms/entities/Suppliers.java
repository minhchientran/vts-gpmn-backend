package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.entities.EntityWithInfo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Suppliers extends EntityWithInfo {
    @Column
    private String companyCode;
    @Column
    private String businessDomain;
    @Column
    private String representationName;
    @Column
    private String email;
    @Column
    private String logo;
    @Column
    private Float rating;
}
