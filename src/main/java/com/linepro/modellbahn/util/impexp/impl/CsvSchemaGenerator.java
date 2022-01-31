package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Column;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component(PREFIX + "CsvSchemaGenerator")
public class CsvSchemaGenerator {

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class CandidateColumn {
        private final String name;
        private final ColumnType type;
    }

    public CsvSchema getSchema(Class<?> modelClass) {
        return getSchema(getCandidates(modelClass));
    }

    public CsvSchema getSchema(Class<?> modelClass, List<String> headerColumns) {
        Map<String,CandidateColumn> classColumns = getCandidates(modelClass).stream()
                                                                            .collect(Collectors.toMap(CandidateColumn::getName, c -> c));

        List<CandidateColumn> columns = headerColumns.stream()
                                                     .map(c -> mapColumn(classColumns, c))
                                                     .collect(Collectors.toList());

        return getSchema(columns);
    }

    protected CandidateColumn mapColumn(Map<String, CandidateColumn> classColumns, String headerName) {
        return Optional.ofNullable(classColumns.get(headerName))
                       .orElse(new CandidateColumn(headerName, ColumnType.STRING));
    }

    protected CsvSchema getSchema(List<CandidateColumn> candidates) {
        AtomicInteger colNumber = new AtomicInteger();

        List<Column> columns = candidates.stream()
                                         .map(c -> new Column(colNumber.getAndIncrement(), c.getName(), c.getType()))
                                         .collect(Collectors.toList());

        return CsvSchema.builder()
                        .addColumns(columns)
                        .build();
    }

    protected List<CandidateColumn> getCandidates(Class<?> modelClass) {
        return Stream.of(modelClass.getDeclaredFields())
                 .filter(f -> !Modifier.isStatic(f.getModifiers()))
                 .filter(f -> !Modifier.isVolatile(f.getModifiers()))
                 .filter(f -> !Modifier.isTransient(f.getModifiers()))
                 .filter(f -> f.getAnnotation(JsonProperty.class) != null)
                 .filter(f -> f.getAnnotation(SuppressExport.class) == null)
                 .filter(f -> !Collection.class.isAssignableFrom(f.getType()))
                 .map(this::getCandidate)
                 .collect(Collectors.toList());
    }

    protected CandidateColumn getCandidate(Field f) {
        String name = StringUtils.defaultIfBlank(f.getAnnotation(JsonProperty.class).value(), f.getName());
        ColumnType type = ColumnType.STRING;

        if (Number.class.isAssignableFrom(f.getType())) {
            type = ColumnType.NUMBER;
        } else if (Boolean.class.isAssignableFrom(f.getType())) {
            type = ColumnType.BOOLEAN;
        }

        return new CandidateColumn(name, type);
    }
}