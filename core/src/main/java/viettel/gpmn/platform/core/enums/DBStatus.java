package viettel.gpmn.platform.core.enums;

import lombok.Getter;

@Getter
public enum DBStatus implements BaseEnum {
    INACTIVE("Ngưng hoạt động"),
    ACTIVE("Hoạt động"),
    DRAFT("Dự thảo"),
    PENDING("Tạm ngưng"),
    CANCEL("Hủy"),
    ;

    private final String value;
    DBStatus(String s) {
        this.value = s;
    }

    DBStatus(Integer i) {
        this.value = String.valueOf(DBStatus.values()[i]);
    }
}
