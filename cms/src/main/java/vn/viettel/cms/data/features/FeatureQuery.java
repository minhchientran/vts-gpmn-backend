package vn.viettel.cms.data.features;

import vn.viettel.core.enums.DBStatus;
import vn.viettel.cms.enums.FeatureType;

public record FeatureQuery (
        String name,
        String description,
        String parentId,
        FeatureType featureType,
        DBStatus status
) {}
