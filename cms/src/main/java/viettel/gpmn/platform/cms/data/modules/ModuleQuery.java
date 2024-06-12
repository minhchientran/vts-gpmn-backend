package viettel.gpmn.platform.cms.data.modules;

import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.enums.Subsystem;

public record ModuleQuery(
        String name,
        String description,
        Subsystem subsystem,
        DBStatus status
) {
}
