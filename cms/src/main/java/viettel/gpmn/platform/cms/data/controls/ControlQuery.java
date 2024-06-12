package viettel.gpmn.platform.cms.data.controls;

import viettel.gpmn.platform.core.enums.DBStatus;

public record ControlQuery(
        String name,
        DBStatus status
) {}
