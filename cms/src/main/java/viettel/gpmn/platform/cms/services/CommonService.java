package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.core.enums.BaseEnum;
import viettel.gpmn.platform.core.services.BaseService;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class CommonService extends BaseService {
    public <E extends Enum<E> & BaseEnum> Object getListEnumInfo(Class<E> enumClass) {
        return new HashMap<String, String>() {{
            for (E value : enumClass.getEnumConstants()) {
                put(value.name(), value.getValue());
            }
        }};
    }

}
