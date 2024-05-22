package vn.viettel.core.enums;

import lombok.Getter;

@Getter
public enum DBStatus {
    INACTIVE("Hết hiệu lực"),
    ACTIVE("Hoạt động"),
    DRAFT("Dự thảo"),
    PENDING("Tạm ngưng"),
    CANCEL("Hủy"),
    ;

    private final String value;
    DBStatus(String s) {
        this.value = s;
    }

}
