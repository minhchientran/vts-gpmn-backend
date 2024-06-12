package viettel.gpmn.platform.cms.enums;

import lombok.Getter;
import viettel.gpmn.platform.core.enums.BaseEnum;

@Getter
public enum FeatureType implements BaseEnum {
    MENU("Menu"),
    FORM("Form"),
    ;

    private final String value;
    FeatureType(String s) {
        this.value = s;
    }

    FeatureType(Integer i) {
        this.value = String.valueOf(FeatureType.values()[i]);
    }
}
