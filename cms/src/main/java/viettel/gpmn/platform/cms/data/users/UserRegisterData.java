package viettel.gpmn.platform.cms.data.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.RetailerData;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterData {
    private UserInfoData userData;
    private RetailerData retailerData;
    private List<String> listSupplierId;
}
