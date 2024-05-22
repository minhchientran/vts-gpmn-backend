package vn.viettel.cms.data.modules;

import vn.viettel.core.enums.DBStatus;
import vn.viettel.core.enums.Subsystem;

public record ModuleQuery(
        String name,
        String description,
        Subsystem subsystem,
        DBStatus status
) {
}
