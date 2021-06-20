package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Column;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType;

@Component(PREFIX + "CsvSchemaGenerator")
public class CsvSchemaGenerator {

    public CsvSchema getSchema(Class<?> modelClass) {
        return getSchema(Stream.of(modelClass.getDeclaredFields())
                               .filter(f -> !Modifier.isStatic(f.getModifiers()))
                               .filter(f -> !Modifier.isVolatile(f.getModifiers()))
                               .filter(f -> !Modifier.isTransient(f.getModifiers()))
                               .filter(f -> f.getAnnotation(JsonProperty.class) != null)
                               .filter(f -> f.getAnnotation(SuppressExport.class) == null)
                               .filter(f -> !Collection.class.isAssignableFrom(f.getType()))
                               .map(f -> new Column(0, getColumnName(f), getColumnType(f)))
                               .collect(Collectors.toList()));
    }

    private String getColumnName(Field f) {
        return StringUtils.defaultIfBlank(f.getAnnotation(JsonProperty.class).value(), f.getName());
    }
    
    public CsvSchema getSchema(List<Column> columns) {
        AtomicInteger colNumber = new AtomicInteger();

        return CsvSchema.builder()
                        .addColumns(
                            columns.stream()
                                   .map(c -> new Column(colNumber.getAndIncrement(),c.getName(), c.getType()))
                                   .collect(Collectors.toList())
                        )
                        .build()
                        .withHeader();
    }

    protected ColumnType getColumnType(Field f) {
        if (Number.class.isAssignableFrom(f.getType())) return ColumnType.NUMBER;

        if (Boolean.class.isAssignableFrom(f.getType())) return ColumnType.BOOLEAN;

        return ColumnType.STRING;
    }
}