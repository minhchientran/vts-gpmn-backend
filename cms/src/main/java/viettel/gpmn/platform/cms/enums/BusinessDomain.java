package viettel.gpmn.platform.cms.enums;

import lombok.Getter;
import viettel.gpmn.platform.core.enums.BaseEnum;

@Getter
public enum BusinessDomain implements BaseEnum {
    IT("Information Technology"),
    RETAIL("Retail"),
    EDUCATION("Education"),
    ;

    private final String value;
    BusinessDomain(String s) {
        this.value = s;
    }
}
