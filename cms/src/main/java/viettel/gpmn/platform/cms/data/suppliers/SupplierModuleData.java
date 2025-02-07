package viettel.gpmn.platform.cms.data.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import viettel.gpmn.platform.core.enums.DBStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierModuleData {
    private String id;
    private String supplierId;
    private String moduleId;
    private LocalDateTime activeDate;
    private Integer activeTime;
    private Integer numberAccount;
    private DBStatus status;
    private LocalDateTime updatedAt;
}
