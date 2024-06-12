package viettel.gpmn.platform.core.enums;


import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.Field;

/***
 * This class is for convert request parameter field to an enum
 * @param <E>
 */
public class EnumConverterFactory<E extends Enum<E>> implements ConverterFactory<String, Enum<E>> {

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends Enum<E>> Converter<String, T> getConverter(@NonNull Class<T> targetType) {
        return source -> {
            for (Field field : targetType.getFields()) {
                if (field.getName().equalsIgnoreCase(source)) {
                    try {
                        return (T) field.get(targetType);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return null;
        };
    }
}
