package vn.viettel.core.enums;

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

    public String getValue() {
        return this.value;
    }
}
