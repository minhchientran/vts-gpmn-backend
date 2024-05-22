package vn.viettel.cms.data.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.viettel.core.data.InfoData;
import vn.viettel.core.enums.Subsystem;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ModuleData extends InfoData {
    private String description;
    private Subsystem subSystem;
    private Integer totalFeatures;
}

