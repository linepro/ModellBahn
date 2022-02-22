package com.linepro.modellbahn.util.impexp.impl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.io.FileStore;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.request.DecoderRequest;
import com.linepro.modellbahn.service.DecoderCreator;

public class DecoderImporterImpl extends ImporterImpl<DecoderRequest, Decoder, DecoderModel> {

    private final Mapper<DecoderRequest, Decoder> mapper;

    private final Lookup<Decoder, DecoderModel> lookup;

    private final DecoderCreator creator;

    public DecoderImporterImpl(JpaRepository<Decoder, Long> repository, Mapper<DecoderRequest, Decoder> mapper, Lookup<Decoder, DecoderModel> lookup,
                    CsvSchemaGenerator generator, FileStore fileStore, DecoderCreator creator) {
        super(repository, mapper, lookup, generator, DecoderRequest.class, fileStore);

        this.mapper = mapper;
        this.lookup = lookup;
        this.creator = creator;
    }

    @Override
    protected Decoder create(DecoderRequest request) {
        Decoder entity = mapper.convert(request);

        Optional<Decoder> existing = lookup.find(entity);
        
        if (!existing.isPresent()) {
           existing = creator.create(request);
        }

        return existing.get();
    }

}
