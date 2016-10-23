package co.rcbike.ventas.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanSNConverter implements AttributeConverter<Boolean, String>{
    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if (Boolean.TRUE.equals(value)) {
            return "S";
        } else {
            return "N";
        }
    }
    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "S".equals(value);
    }
}