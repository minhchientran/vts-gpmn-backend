package vn.viettel.cms.data.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.viettel.core.data.InfoData;
import vn.viettel.core.enums.Subsystem;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ModuleData extends InfoData {
    private String description;
    private Subsystem subsystem = Subsystem.ADMIN;
    private Integer totalFeatures = 1;
}

