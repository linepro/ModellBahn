package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Column;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType;
import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.util.impexp.Exporter;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class Exporter.
 * @param <E> the element type
 */
@Slf4j
public class ExporterImpl<M extends ItemModel,E extends Item> implements Exporter {

    private static final List<String> EXCLUDED = Arrays.asList("serialVersionUID", "links");
    
    private static final Integer PAGE_SIZE = 100;

    private final PagingAndSortingRepository<E,Long> repository;

    private final Mutator<E,M> mutator;

    private final CsvMapper mapper;

    private final Class<M> modelClass;

    @SuppressWarnings("unchecked")
    public ExporterImpl(PagingAndSortingRepository<E,Long> repository, Mutator<E,M> mutator, CsvMapper mapper) {
        this.repository = repository;
        this.mutator = mutator;
        this.mapper =  (CsvMapper) mapper.configure(Feature.IGNORE_UNKNOWN, true);

        modelClass = (Class<M>) mutator.get().getClass();
    }

    @Override
    @Transactional
    public void write(Writer out) throws IOException {
        try (SequenceWriter writer = mapper.writerFor(modelClass)
                                      .with(getSchema())
                                      .writeValues(out)) {
            for (int pageNumber = 0; ; pageNumber++) {
                Page<E> page = repository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
    
                if (page.hasContent()) {
                    page.getContent()
                        .stream()
                        .map(i -> attempt(() -> writer.write(mutator.convert(i))))
                        .collect(success())
                        .orElseThrow();
                } else {
                    break;
                }
            }
        } catch(Exception e) {
            throw e;
        }
    }
    
    protected CsvSchema getSchema() {
        AtomicInteger colNumber = new AtomicInteger();
        List<Column> columns =  Stream.of(modelClass.getDeclaredFields())
                                      .filter(f -> !EXCLUDED.contains(f.getName()))
                                      .map(f -> new Column(colNumber.getAndIncrement(), f.getName(), getColumnType(f)))
                                      .collect(Collectors.toList());
                        
        return CsvSchema.builder()
                        .addColumns(columns)
                        .build()
                        .withHeader();
    }

    protected ColumnType getColumnType(Field f) {
        if (f.getType().isAssignableFrom(Number.class)) return ColumnType.NUMBER;

        return ColumnType.STRING;
    }
}
