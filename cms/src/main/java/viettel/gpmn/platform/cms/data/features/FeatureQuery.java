package viettel.gpmn.platform.cms.data.features;

import viettel.gpmn.platform.cms.enums.FeatureType;
import viettel.gpmn.platform.core.enums.DBStatus;

import java.util.List;

public record FeatureQuery (
        String name,
        String parentId,
        List<FeatureType> featureType,
        List<DBStatus> status
) {}
