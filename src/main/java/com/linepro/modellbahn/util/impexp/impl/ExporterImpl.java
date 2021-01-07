package com.linepro.modellbahn.util.impexp.impl;

import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import java.io.Writer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.linepro.modellbahn.controller.impl.ApiMessages;
import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Item;
import com.linepro.modellbahn.model.ItemModel;
import com.linepro.modellbahn.util.exceptions.ModellBahnException;
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

    private final CsvSchema schema;

    public ExporterImpl(PagingAndSortingRepository<E,Long> repository, Mutator<E,M> mutator, CsvSchemaGenerator generator, Class<M> modelClass) {
        this.repository = repository;
        this.mutator = mutator;
        this.modelClass = modelClass;
        this.schema = generator.getSchema(modelClass);
    }

    @Override
    @Transactional
    public void write(Writer out) {
        try (SequenceWriter writer = MAPPER.writerFor(modelClass)
                                           .with(schema)
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
        } catch (Exception e) {
            throw ModellBahnException.raise(ApiMessages.EXPORT_ERROR, e);
        }
    }
}
