package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.entities.EntityWithInfo;

import java.time.LocalDateTime;

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
    private String address;
    @Column
    private String logo;
    @Column
    private LocalDateTime activeDate;
    @Column
    private LocalDateTime endDate;
    @Column
    private Float rating;
}
