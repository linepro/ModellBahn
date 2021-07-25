package com.linepro.modellbahn.util.impexp.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.io.LineReader;
import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.request.ItemRequest;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;
import com.linepro.modellbahn.util.impexp.Importer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImporterImpl<R extends ItemRequest, E extends Item> implements Importer {

    private static final CsvMapper MAPPER = CsvMapper.builder()
                    .findAndAddModules()
                    .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                    .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                    .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                    .build();

    private final JpaRepository<E,Long> repository;

    private final Mapper<R,E> mapper;

    private final Class<R> requestClass;

    private final CsvSchemaGenerator generator;

    public ImporterImpl(JpaRepository<E,Long> repository, Mapper<R,E> mapper, CsvSchemaGenerator generator, Class<R> requestClass) {
        this.repository = repository;
        this.mapper = mapper;
        this.generator = generator;
        this.requestClass = requestClass;
    }

    @Override
    @Transactional
    public void read(Reader in) {
        try {
            String headerLine = new LineReader(in).readLine();

            // Filter schema with columns actually in input file header and re-create using specified order...
            CsvSchema schema = generator.getSchema(requestClass, Arrays.asList(headerLine.split(",")));

            ObjectReader reader = MAPPER.readerFor(requestClass).with(schema);

            MappingIterator<R> mi = reader.readValues(in);

            List<String> errors = new ArrayList<>();
            Integer rowNum = 0;


            while (mi.hasNext()) {
                rowNum++;

                R next = mi.next();

                try {
                    repository.save(mapper.convert(next));
                } catch(Exception e) {
                    String error = getError(e);

                    errors.add(MessageFormatter.arrayFormat("#{} - '{}': {}", new Object[] { rowNum, next, error }).getMessage());

                    log.error("Error importing #{} - '{}': {}", rowNum, next, error, e);
                }
            }

            if (!CollectionUtils.isEmpty(errors)) {
                throw ModellBahnException.raise(ApiMessages.IMPORT_ERROR)
                                         .addValue(errors.stream().collect(Collectors.joining("\n")))
                                         .setStatus(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeJsonMappingException e) {
            throw ModellBahnException.raise(ApiMessages.IMPORT_ERROR, e)
                                     .addValue(e.getMessage())
                                     .setStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw ModellBahnException.raise(ApiMessages.IMPORT_ERROR, e);
        }
    }

    private String getError(Exception e) {
        String error;

        if (e instanceof ConstraintViolationException) {
            error = ((ConstraintViolationException) e).getConstraintViolations()
                                                       .stream()
                                                       .map(c -> c.getMessage())
                                                       .collect(Collectors.joining(","));
        } else if (e.getCause() != null) {
            error = e.getCause().getMessage();
        } else {
            error = e.getMessage();
        }
        return error;
    }
}