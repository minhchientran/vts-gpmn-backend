package com.example.authservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithInfo;
import vn.viettel.core.enums.Subsystem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "features")
public class Features extends EntityWithInfo {
    @Column
    private String parentFeatureId;

    @Column
    private Integer type;

    @Column
    private Integer seq;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Subsystem subsystem;

    @Column
    private String description;
}
