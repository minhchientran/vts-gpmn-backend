package viettel.gpmn.platform.cms.data.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import viettel.gpmn.platform.core.data.InfoData;
import viettel.gpmn.platform.core.enums.Subsystem;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ModuleData extends InfoData {
    private String description;
    private Subsystem subsystem = Subsystem.ADMIN;
    private Integer totalFeatures = 1;
}

