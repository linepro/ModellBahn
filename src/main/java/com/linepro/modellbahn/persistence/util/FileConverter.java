package com.linepro.modellbahn.persistence.util;

import java.io.File;

import javax.persistence.AttributeConverter;

public class FileConverter implements AttributeConverter<File, String> {

    @Override
    public String convertToDatabaseColumn(File attribute) {
        if (attribute != null) {
            return attribute.getAbsolutePath();
        }

        return null;
    }

    @Override
    public File convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            return new File(dbData);
        }

        return null;
    }
}
