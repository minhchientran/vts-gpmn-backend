package vn.viettel.cms.enums;

import lombok.Getter;
import vn.viettel.core.enums.BaseEnum;

@Getter
public enum FeatureType implements BaseEnum {
    FORM("Form"),
    MENU("Menu"),
    ;

    private final String value;
    FeatureType(String s) {
        this.value = s;
    }

    FeatureType(Integer i) {
        this.value = String.valueOf(FeatureType.values()[i]);
    }
}
