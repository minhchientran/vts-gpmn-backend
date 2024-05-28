package vn.viettel.core.enums;

import lombok.Getter;

@Getter
public enum FeatureType implements BaseEnum {
    FROM("Form"),
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
