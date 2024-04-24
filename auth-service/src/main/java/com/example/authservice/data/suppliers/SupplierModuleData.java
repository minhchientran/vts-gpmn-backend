package com.example.authservice.data.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.viettel.core.enums.DBStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SupplierModuleData {
    private String supplierId;
    private String moduleId;
    private LocalDateTime activeDate;
    private Integer activeTime;
    private Integer numberAccount;
    private DBStatus status;
}
