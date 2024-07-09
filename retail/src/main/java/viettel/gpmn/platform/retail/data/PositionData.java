package viettel.gpmn.platform.retail.data;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettel.gpmn.platform.core.data.BaseData;

@Getter
@Setter
@NoArgsConstructor
public class PositionData extends BaseData {
    private String name;

    public PositionData(
            String id,
            String name
    ) {
        setId(id);
        this.name = name;
    }

}
