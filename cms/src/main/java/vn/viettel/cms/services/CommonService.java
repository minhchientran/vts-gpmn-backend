package vn.viettel.cms.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.viettel.core.enums.*;
import vn.viettel.core.services.BaseService;

import java.lang.reflect.Field;
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
