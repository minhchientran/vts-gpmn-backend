package viettel.gpmn.platform.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.entities.EntityWithAware;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo extends EntityWithAware {
    @Column
    private String userId;
    @Column
    private String name;
    @Column
    private LocalDate birthDate;
    @Column
    private Integer gender;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String invite_code;
    @Column
    private String ref_code;
    @Column
    private String tax_number;
    @Column
    private String citizenIdentificationNumber;
    @Column
    private String imageCardFront;
    @Column
    private String imageCardBack;
}
