package vn.viettel.cms.enums;

import lombok.Getter;
import vn.viettel.core.enums.BaseEnum;

@Getter
public enum ControlType implements BaseEnum {
    BUTTON("Button"),
    SEARCH_PANEL("Search Panel"),
    GRID("Grid"),
    DATA("Data"),
    ;

    private final String value;
    ControlType(String s) {
        this.value = s;
    }
}
