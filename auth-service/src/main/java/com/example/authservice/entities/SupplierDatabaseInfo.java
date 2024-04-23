package com.example.authservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import vn.viettel.core.entities.EntityWithAwareInfo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_database_info")
public class SupplierDatabaseInfo extends EntityWithAwareInfo {
    @Column
    private String url;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String supplierId;
}
