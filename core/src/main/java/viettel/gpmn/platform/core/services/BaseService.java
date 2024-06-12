package viettel.gpmn.platform.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    protected ObjectMapper objectMapper;
}
