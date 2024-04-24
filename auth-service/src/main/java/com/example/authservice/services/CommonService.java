package com.example.authservice.services;

import org.springframework.stereotype.Service;
import vn.viettel.core.enums.DBStatus;
import vn.viettel.core.services.BaseService;

import java.lang.reflect.Field;
import java.util.HashMap;

@Service
public class CommonService extends BaseService {
    public Object getListStatus() {
        return new HashMap<String, String>() {{
            for (Field field : DBStatus.class.getFields()) {
                put(field.getName(), DBStatus.valueOf(field.getName()).getValue());
            }
        }};
    }
}
