package com.linepro.modellbahn.persistence.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.persistence.AttributeConverter;

public class URLConverter implements AttributeConverter<URL, String> {

    @Override
    public String convertToDatabaseColumn(URL attribute) {
        if (attribute != null) {
            return attribute.toExternalForm();
        }

        return null;
    }

    @Override
    public URL convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            try {
                return new URL(dbData);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return null;
    }
}
