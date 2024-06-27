package viettel.gpmn.platform.retail.data.staff;

import viettel.gpmn.platform.core.enums.DBStatus;

import java.util.List;

public record StaffQuery(
        String name,
        List<String> positionId,
        List<String> unitId,
        List<DBStatus> status
) {
}
