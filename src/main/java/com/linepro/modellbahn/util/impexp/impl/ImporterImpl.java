package com.linepro.modellbahn.util.impexp.impl;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.util.impexp.Importer;

public class ImporterImpl<M extends ItemModel,E extends Item> implements Importer {

    private final JpaRepository<E,Long> repository;

    private final Mutator<M,E> mutator;

    private final CsvMapper mapper;

    private final CsvSchema schema; 

    private final Class<M> modelClass;
    
    @SuppressWarnings("unchecked")
    public ImporterImpl(JpaRepository<E,Long> repository, Mutator<M,E> mutator, CsvMapper mapper) {
        this.repository = repository;
        this.mutator = mutator;
        this.mapper = mapper;
        
        modelClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass())
                                                               .getActualTypeArguments()[0];
        schema = CsvSchema.emptySchema().withHeader();
    }
    
    @Override
    @Transactional
    public void read(Reader in) throws IOException {
        ObjectReader reader = mapper.readerFor(modelClass).with(schema);

        MappingIterator<M> mi = reader.readValues(in);

        while (mi.hasNext()) {
            repository.save(mutator.convert(mi.next()));
        }
    }
}