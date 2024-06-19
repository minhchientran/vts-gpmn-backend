package viettel.gpmn.platform.retail.data.staff;

import viettel.gpmn.platform.core.enums.DBStatus;

public record StaffQuery(
        String name,
        String positionId,
        String unitId,
        DBStatus status
) {
}
