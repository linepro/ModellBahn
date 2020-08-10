package com.linepro.modellbahn.util.impexp.impl;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;
import com.linepro.modellbahn.util.impexp.Importer;

public class ImporterImpl<M extends ItemModel,E extends Item> implements Importer {

    private static final CsvMapper MAPPER = CsvMapper.builder()
                    .findAndAddModules()
                    .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .build();

    private final JpaRepository<E,Long> repository;

    private final Mutator<M,E> mutator;

    private final CsvSchema schema; 

    private final Class<M> modelClass;
    
    @SuppressWarnings("unchecked")
    public ImporterImpl(JpaRepository<E,Long> repository, Mutator<M,E> mutator) {
        this.repository = repository;
        this.mutator = mutator;
        
        modelClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass())
                                                               .getActualTypeArguments()[0];
        schema = CsvSchema.emptySchema().withHeader();
    }
    
    @Override
    @Transactional
    public void read(Reader in) {
        MappingIterator<M> mi;
        try {
            ObjectReader reader = MAPPER.readerFor(modelClass).with(schema);

            mi = reader.readValues(in);

            while (mi.hasNext()) {
                repository.save(mutator.convert(mi.next()));
            }
        } catch (IOException e) {
            throw ModellBahnException.raise("{}", e).setStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw ModellBahnException.raise("{}", e);
        }
    }
}