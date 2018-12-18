package com.linepro.modellbahn.persistence.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.persistence.AttributeConverter;

class FileConverter implements AttributeConverter<Path, String> {

    @Override
    public String convertToDatabaseColumn(Path attribute) {
        if (attribute != null) {
            return attribute.toString();
        }

        return null;
    }

    @Override
    public Path convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            return Paths.get(dbData);
        }

        return null;
    }
}
