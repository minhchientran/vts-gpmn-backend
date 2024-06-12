package viettel.gpmn.platform.cms.data.suppliers;

import viettel.gpmn.platform.core.enums.DBStatus;

public record SupplierQuery (
        String name,
        String code,
        DBStatus status
) {
}
