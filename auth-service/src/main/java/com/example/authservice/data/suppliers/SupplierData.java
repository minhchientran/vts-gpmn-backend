package com.example.authservice.data.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.viettel.core.enums.DBStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierData {
    private String code;
    private String identityCode;
    private String name;
    private String representationName;
    private String email;
    private String businessDomain;
    private String logoFileName;
    private String logoFileData;
    private DBStatus status;
}
