package vn.viettel.core.enums;

import lombok.Getter;

@Getter
public enum Subsystem {
    CMS("CMS"),
    ADMIN("Admin"),
    RETAIL("Retail"),

    PG("PG"),
    ;

    private final String value;
    Subsystem(String s) {
        this.value = s;
    }

}
