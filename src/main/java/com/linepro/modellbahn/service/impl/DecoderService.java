package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.DecoderTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.DecoderModelTranscriber;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.DecoderAdressRepository;
import com.linepro.modellbahn.repository.DecoderCvRepository;
import com.linepro.modellbahn.repository.DecoderFunktionRepository;
import com.linepro.modellbahn.repository.DecoderRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * DecoderService. CRUD service for Decoder
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class DecoderService extends ItemServiceImpl<DecoderModel,Decoder> implements ItemService<DecoderModel> {

    private final DecoderRepository repository;
    private final DecoderAdressRepository adressRepository;
    private final DecoderCvRepository cvRepository;
    private final DecoderFunktionRepository funktionRepository;
    
    @Autowired
    public DecoderService(DecoderRepository repository, DecoderAdressRepository adressRepository, DecoderCvRepository cvRepository, DecoderFunktionRepository funktionRepository) {
        super(repository, 
              new MutatorImpl<DecoderModel, Decoder>(() -> new Decoder(), new DecoderModelTranscriber()),
              new MutatorImpl<Decoder, DecoderModel>(() -> new DecoderModel(), new DecoderTranscriber())
              );
        this.repository = repository;
        this.adressRepository = adressRepository;
        this.cvRepository = cvRepository;
        this.funktionRepository = funktionRepository;
    }

    public Optional<DecoderModel> add(String herstellerStr, String bestellNr) {
        return Optional.empty();
    }

    public Optional<DecoderModel> get(String decoderId) {
        return super.get(() -> repository.findByDecoderId(decoderId));
    }

    public Optional<DecoderModel> update(String decoderId, DecoderModel model) {
        return super.update(() -> repository.findByDecoderId(decoderId), model);
    }

    public boolean delete(String decoderId) {
        return super.delete(() -> repository.findByDecoderId(decoderId));
    }

    public Optional<DecoderAdressModel> updateAdress(String decoderId, Integer index, Integer adress) {
        return Optional.empty();
    }

    public Optional<DecoderCvModel> updateCv(String decoderId, Integer cv, Integer wert) {
        return Optional.empty();
    }

    public Optional<DecoderFunktionModel> updateFunktion(String decoderId, Integer reihe, String funktion, String bezeichnung) {
        return Optional.empty();
    }
}
