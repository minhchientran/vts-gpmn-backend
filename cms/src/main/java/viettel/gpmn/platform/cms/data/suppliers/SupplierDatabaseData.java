package viettel.gpmn.platform.cms.data.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDatabaseData {
    private String url;
    private String username;
    private String password;
}
