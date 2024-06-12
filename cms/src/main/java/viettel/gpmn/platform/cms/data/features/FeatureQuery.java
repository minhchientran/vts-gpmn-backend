package viettel.gpmn.platform.cms.data.features;

import viettel.gpmn.platform.cms.enums.FeatureType;
import viettel.gpmn.platform.core.enums.DBStatus;

public record FeatureQuery (
        String name,
        String description,
        String parentId,
        FeatureType featureType,
        DBStatus status
) {}
