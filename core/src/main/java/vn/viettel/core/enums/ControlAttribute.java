package vn.viettel.core.enums;

public enum ControlAttribute {
    DISABLE("Disable"),
    ENABLE("Enable"),
    INVISIBLE("Invisible"),
    ;

    private final String value;
    ControlAttribute(String s) {
        this.value = s;
    }
}
