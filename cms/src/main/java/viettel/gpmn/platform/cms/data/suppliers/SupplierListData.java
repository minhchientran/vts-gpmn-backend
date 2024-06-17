package viettel.gpmn.platform.cms.data.suppliers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import viettel.gpmn.platform.core.data.InfoData;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SupplierListData extends InfoData {
    private String companyCode;
    private LocalDateTime activeDate;
    private LocalDateTime endDate;
}
