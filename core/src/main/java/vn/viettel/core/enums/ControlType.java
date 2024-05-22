package vn.viettel.core.enums;

import lombok.Getter;

@Getter
public enum ControlType {
    BUTTON("Button"),
    ;

    private final String value;
    ControlType(String s) {
        this.value = s;
    }
}
