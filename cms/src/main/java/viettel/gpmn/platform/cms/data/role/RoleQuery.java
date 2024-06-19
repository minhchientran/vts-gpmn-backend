package viettel.gpmn.platform.cms.data.role;

import viettel.gpmn.platform.core.enums.DBStatus;

import java.util.List;

public record RoleQuery(
        String name,
        List<DBStatus> status
) {
}
