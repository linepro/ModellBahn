package com.linepro.modellbahn.util.impexp.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

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
import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;
import com.linepro.modellbahn.util.impexp.Importer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImporterImpl<M extends ItemModel,E extends Item> implements Importer {

    private static final CsvMapper MAPPER = CsvMapper.builder()
                    .findAndAddModules()
                    .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                    .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                    .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                    .build();

    private final JpaRepository<E,Long> repository;

    private final Mutator<M,E> mutator;

    private final CsvSchema schema; 

    private final Class<M> modelClass;
    
    public ImporterImpl(JpaRepository<E,Long> repository, Mutator<M,E> mutator, Class<M> modelClass) {
        this.repository = repository;
        this.mutator = mutator;
        this.modelClass = modelClass;
        this.schema = new CsvSchemaGenerator().getSchema(modelClass);
    }
    
    @Override
    @Transactional
    public void read(Reader in) {
        MappingIterator<M> mi;

        List<String> errors = new ArrayList<>();
        int rowNum = 0;

        try {
            ObjectReader reader = MAPPER.readerFor(modelClass).with(schema);

            mi = reader.readValues(in);

            while (mi.hasNext()) {
                rowNum++;

                M next = mi.next();

                try {
                    repository.save(mutator.convert(next));
                } catch(Exception e) {
                    String actual;

                    if (e instanceof ConstraintViolationException) {
                        actual = ((ConstraintViolationException) e).getConstraintViolations()
                                                                   .stream()
                                                                   .map(c -> c.getMessage())
                                                                   .collect(Collectors.joining(","));
                    } else if (e.getCause() != null) {
                        actual = e.getCause().getMessage();
                    } else {
                        actual = e.getMessage();
                    }
                    
                    String error = String.format("#%d - '%s': %s", rowNum, next, actual);
                    errors.add(error);
                    log.error(error, e);
                }
            }
        } catch (RuntimeJsonMappingException e) {
            throw ModellBahnException.raise(ApiMessages.IMPORT_ERROR, e)
                                     .addValue(e.getMessage())
                                     .setStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw ModellBahnException.raise(ApiMessages.IMPORT_ERROR, e);
        }
        
        if (!CollectionUtils.isEmpty(errors)) {
            throw ModellBahnException.raise(ApiMessages.IMPORT_ERROR)
                                     .addValue(errors.stream().collect(Collectors.joining("\n")))
                                     .setStatus(HttpStatus.BAD_REQUEST);
        }
    }
}