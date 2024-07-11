package viettel.gpmn.platform.cms.data.suppliers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.BaseData;
import viettel.gpmn.platform.core.enums.DBStatus;

@Getter
@Setter
@NoArgsConstructor
public class SupplierForUserData extends BaseData {
    private String name;
    private String address;
    private String logo;
    private Float rating;
    private Boolean isRegister;

    public SupplierForUserData(
            String id,
            String name,
            String address,
            String logo,
            Float rating,
            DBStatus registerStatus
    ) {
        setId(id);
        this.name = name;
        this.address = address;
        this.logo = logo;
        this.rating = rating;
        this.isRegister = DBStatus.ACTIVE.equals(registerStatus);
    }
}
