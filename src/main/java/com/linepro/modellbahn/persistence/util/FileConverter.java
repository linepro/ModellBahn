package com.linepro.modellbahn.persistence.util;

import javax.persistence.AttributeConverter;
import java.nio.file.Path;
import java.nio.file.Paths;

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
