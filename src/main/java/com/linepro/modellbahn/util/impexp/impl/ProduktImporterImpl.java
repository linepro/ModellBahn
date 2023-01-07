package com.linepro.modellbahn.util.impexp.impl;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.entity.ProduktDecoderTyp;
import com.linepro.modellbahn.io.FileStore;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.repository.lookup.Lookup;
import com.linepro.modellbahn.request.ProduktRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProduktImporterImpl extends ImporterImpl<ProduktRequest, Produkt, ProduktModel> {

    private final JpaRepository<Produkt, Long> repository;
    private Mapper<ProduktRequest, Produkt> mapper;
    private Lookup<Produkt, ProduktModel> lookup;
    
    public ProduktImporterImpl(JpaRepository<Produkt, Long> repository, Mapper<ProduktRequest, Produkt> mapper, Lookup<Produkt, ProduktModel> lookup,
                    CsvSchemaGenerator generator, FileStore fileStore) {
        super(repository, mapper, lookup, generator, ProduktRequest.class, fileStore);

        this.repository = repository;
        this.mapper = mapper;
        this.lookup = lookup;
    }

    @Override
    protected void processRow(List<Field> fileFields, ObjectReader reader, int rowNum, List<String> errors, String line)
                    throws JsonProcessingException, JsonMappingException {
        ProduktRequest request = reader.readValue(line);

        Produkt produkt = mapper.convert(request);

        Set<ProduktDecoderTyp> typen = new HashSet<>(produkt.getDecoderTypen());
        produkt.getDecoderTypen().clear();

        produkt = lookup.find(produkt)
                        .map(p -> mapper.apply(request, p))
                        .orElse(produkt);
        
        boolean isNew = produkt.getId() == null;

        produkt = addFileNames(fileFields, produkt, request);

        try {
            produkt = repository.saveAndFlush(produkt);
            
            if (isNew && !typen.isEmpty()) {
                for (ProduktDecoderTyp t : typen) {
                    produkt.addDecoderTyp(t.getDecoderTyp());
                }
                repository.saveAndFlush(produkt);
            }
        } catch (Exception e) {
            String error = getError(rowNum, request, e);

            errors.add(error);

            log.error("Error importing {}: {}", error, e);
        }
    }
}
