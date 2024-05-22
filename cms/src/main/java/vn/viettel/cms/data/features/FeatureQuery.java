package vn.viettel.cms.data.features;

import vn.viettel.core.enums.DBStatus;

public record FeatureQuery (
        String name,
        String description,
        String parentId,
        Integer type,
        DBStatus status
) {
}
