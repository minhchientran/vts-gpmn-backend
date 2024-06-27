package viettel.gpmn.platform.cms.data.controls;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.cms.enums.ControlType;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.ControlAttribute;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierControlData extends InfoData {
    private String supplierId;
    private String controlId;
    private ControlType controlType;
    private ControlAttribute controlAttribute;

    public SupplierControlData(
        String id,
        String code,
        String name,
        String supplierId,
        String controlId,
        ControlType controlType,
        ControlAttribute controlAttribute
    ) {
        setId(id);
        setCode(code);
        setName(name);
        this.supplierId = supplierId;
        this.controlId = controlId;
        this.controlType = controlType;
        this.controlAttribute = controlAttribute;
    }
}
