package viettel.gpmn.platform.cms.data.role;

import viettel.gpmn.platform.cms.enums.FeatureType;
import viettel.gpmn.platform.core.enums.DBStatus;

import java.util.List;

public record RoleFeatureQuery (
    String name,
    List<String> parentId,
    List<FeatureType> featureType,
    List<String> moduleId,
    List<DBStatus> status
){}
