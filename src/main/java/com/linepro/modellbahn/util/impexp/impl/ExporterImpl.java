package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Column;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType;
import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.util.impexp.Exporter;

/**
 * The Class Exporter.
 * @param <E> the element type
 */
public class ExporterImpl<M extends ItemModel,E extends Item> implements Exporter {

    private static final CsvMapper MAPPER = CsvMapper.builder()
                    .findAndAddModules()
                    .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                    .build();

    private static final Integer PAGE_SIZE = 100;

    private final PagingAndSortingRepository<E,Long> repository;

    private final Mutator<E,M> mutator;

    private final Class<M> modelClass;

    @SuppressWarnings("unchecked")
    public ExporterImpl(PagingAndSortingRepository<E,Long> repository, Mutator<E,M> mutator) {
        this.repository = repository;
        this.mutator = mutator;

        modelClass = (Class<M>) mutator.get().getClass();
    }

    @Override
    @Transactional
    public void write(Writer out) throws IOException {
        try (SequenceWriter writer = MAPPER.writerFor(modelClass)
                                      .with(getSchema())
                                      .writeValues(out)) {
            for (int pageNumber = 0; ; pageNumber++) {
                Page<E> page = repository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
    
                if (page.hasContent()) {
                    page.getContent()
                        .stream()
                        .map(i -> mutator.convert(i))
                        .map(i -> attempt(() -> writer.write(i)))
                        .collect(success())
                        .orElseThrow();
                    
                    if (page.isLast()) {
                        break;
                    }
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
                                      .filter(f -> !Modifier.isStatic(f.getModifiers()))
                                      .filter(f -> !Modifier.isVolatile(f.getModifiers()))
                                      .filter(f -> !Modifier.isTransient(f.getModifiers()))
                                      .filter(f -> !Collection.class.isAssignableFrom(f.getType()))
                                      .filter(f -> f.getAnnotation(JsonProperty.class) != null)
                                      .map(f ->
                                           new Column(
                                               colNumber.getAndIncrement(),
                                               StringUtils.defaultIfBlank(f.getAnnotation(JsonProperty.class).value(), f.getName()),
                                               getColumnType(f)))
                                      .collect(Collectors.toList());

        System.out.println(
                        columns.stream()
                               .map(c -> String.join(":", c.getName(), Integer.toString(c.getIndex()), c.getType().name()))
                               .collect(Collectors.joining(", "))
                               );

        return CsvSchema.builder()
                        .addColumns(columns)
                        .build()
                        .withHeader();
    }

    protected ColumnType getColumnType(Field f) {
        if (Number.class.isAssignableFrom(f.getType())) return ColumnType.NUMBER;

        if (Boolean.class.isAssignableFrom(f.getType())) return ColumnType.BOOLEAN;

        return ColumnType.STRING;
    }
}
