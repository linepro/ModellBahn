package com.linepro.modellbahn.persistence.util;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute != null) {
            return attribute.toString();
        }

        return null;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            return Boolean.valueOf(dbData);
        }

        return null;
    }
}
