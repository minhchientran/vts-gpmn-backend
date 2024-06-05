package vn.viettel.cms.configs;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import vn.viettel.cms.enums.FeatureType;

public class FeatureTypeConverter implements Converter<String, FeatureType> {
    @Override
    public FeatureType convert(@NonNull String source) {
        try {
            return FeatureType.valueOf(source.toUpperCase());
        } catch(Exception e) {
            return null;
        }
    }
}
