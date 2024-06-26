package viettel.gpmn.platform.cms.data.controls;

import viettel.gpmn.platform.core.enums.DBStatus;

import java.util.List;

public record ControlQuery(
        String name,
        List<DBStatus> status
) {}
