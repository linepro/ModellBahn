package com.linepro.modellbahn.persistence.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.persistence.AttributeConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
                log.error("Cannont convert {} to url: []", dbData, e.getMessage());
            }
        }

        return null;
    }
}
