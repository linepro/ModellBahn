package com.linepro.modellbahn.util.impexp.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.io.LineReader;
import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.request.ItemRequest;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;
import com.linepro.modellbahn.util.impexp.Importer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImporterImpl<R extends ItemRequest, E extends Item, M extends ItemModel> implements Importer {

    private static final CsvMapper MAPPER = CsvMapper.builder()
                    .findAndAddModules()
                    .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                    .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                    .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                    .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
                    .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                    .build();

    private final JpaRepository<E,Long> repository;

    private final Mapper<R,E> mapper;

    private final CommitterImpl<R,E,M> committer;

    private final Class<R> requestClass;

    private final CsvSchemaGenerator generator;

    private final Lookup<E,M> lookup;

    public ImporterImpl(JpaRepository<E,Long> repository, Mapper<R,E> mapper, Lookup<E,M> lookup, CommitterImpl<R,E,M> committer, CsvSchemaGenerator generator, Class<R> requestClass) {
        this.repository = repository;
        this.mapper = mapper;
        this.committer = committer;
        this.generator = generator;
        this.requestClass = requestClass;
        this.lookup = lookup;
    }

    @Override
    public void read(Reader in) {
        try {
            LineReader lineReader = new LineReader(in);

            // Filter schema with columns actually in input file header and re-create using specified order...
            CsvSchema schema = generator.getSchema(requestClass, columnNames(lineReader.readLine()));

            ObjectReader reader = MAPPER.readerFor(requestClass).with(schema);

            int rowNum = 0;
            List<String> errors = new ArrayList<>();

            for (String line = lineReader.readLine(); line != null; rowNum++, line = lineReader.readLine()) {
                try {
                    R request = reader.readValue(line);

                    E model = mapper.convert(request);
                    
                    try {
                        committer.saveOrUpdate(repository, lookup, mapper, rowNum, request, model, errors);
                    } catch (UnexpectedRollbackException e) {
                    }
                } catch(Exception e) {
                    String error = getError(rowNum, "unreadable entry", e);

                    errors.add(error);

                    log.error("Error importing {}: {}", error, e);
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
        } catch (ModellBahnException e) {
            throw e;
        } catch (Exception e) {
            throw ModellBahnException.raise(ApiMessages.IMPORT_ERROR, e);
        }
    }

    /**
     * handle "name","name" and name,name formats
     * @param headerLine the header
     * @return a list of names without enclosing '"'
     */
    protected List<String> columnNames(String headerLine) {
        return Arrays.asList(
                        headerLine.replace("\"", "")
                        .split(","));
    }

    private String getError(int rowNum, Object request, Throwable e) {
        String error = null;

        if (e.getCause() != null) {
            error = e.getCause().getMessage();
        }
        
        if (!StringUtils.hasText(error)) {
            error = e.getMessage();
        }

        if (!StringUtils.hasText(error)) {
            error = e.getClass().getSimpleName();
        }

        return MessageFormatter.arrayFormat("#{} - {}: {}", new Object[] { rowNum, request, error }).getMessage();
    }
}