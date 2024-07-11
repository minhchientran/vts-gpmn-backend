package viettel.gpmn.platform.core.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetailerData {
    private String retailerName;
    private String phone;
    private String areaId;
    private String address;
    private Float longitude;
    private Float latitude;
}
