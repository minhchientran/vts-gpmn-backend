package vn.viettel.core.enums;

import lombok.Getter;

@Getter
public enum Subsystem implements BaseEnum {
    CMS("CMS"),
    ADMIN("Admin"),
    RETAIL_BUY("Retail mua hàng"),
    RETAIL_SELL("Retail bán hàng"),
    CONSUMER("Người tiêu dùng"),
    PG("PG"),
    ;

    private final String value;
    Subsystem(String s) {
        this.value = s;
    }

    Subsystem(Integer i) {
        this.value = String.valueOf(Subsystem.values()[i]);
    }

}
