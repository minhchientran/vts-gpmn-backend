package viettel.gpmn.platform.cms.data.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SupplierData extends SupplierListData {
    private String representationName;
    private String email;
    private String businessDomain;
    private String address;
    private String logoFileName;
    private String logoFileData;
    private String logo;
    private List<SupplierModuleData> listSupplierModule;
}
