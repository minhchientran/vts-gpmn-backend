package viettel.gpmn.platform.cms.data.modules;

import viettel.gpmn.platform.core.enums.DBStatus;
import viettel.gpmn.platform.core.enums.Subsystem;

import java.util.List;

public record ModuleQuery(
        String name,
        List<Subsystem> subsystem,
        List<DBStatus> status
) {
}
