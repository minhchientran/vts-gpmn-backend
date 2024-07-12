package viettel.gpmn.platform.core.configs.jpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Value;

@Converter
public class LinkConverter implements AttributeConverter<String, String> {

    @Value("${info.storage-service:}")
    private String storageUrl;

    @Override
    public String convertToDatabaseColumn(String s) {
        return s;
    }

    @Override
    public String convertToEntityAttribute(String s) {
        if (s!= null && !s.isEmpty()) {
            return storageUrl + s;
        }
        return s;
    }
}
