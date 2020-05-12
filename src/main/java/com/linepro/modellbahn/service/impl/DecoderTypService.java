package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.DecoderTypTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.DecoderTypModelTranscriber;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.repository.DecoderTypAdressRepository;
import com.linepro.modellbahn.repository.DecoderTypCvRepository;
import com.linepro.modellbahn.repository.DecoderTypFunktionRepository;
import com.linepro.modellbahn.repository.DecoderTypRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * AchsfolgService. CRUD service for DecoderTyp
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class DecoderTypService extends ItemServiceImpl<DecoderTypModel,DecoderTyp> implements ItemService<DecoderTypModel> {

    private final DecoderTypRepository repository;
    private final DecoderTypAdressRepository adressRepository;
    private final DecoderTypCvRepository cvRepository;
    private final DecoderTypFunktionRepository funktionRepository;

    @Autowired
    public DecoderTypService(DecoderTypRepository repository, DecoderTypAdressRepository adressRepository, DecoderTypCvRepository cvRepository, DecoderTypFunktionRepository funktionRepository) {
        super(repository, 
              new MutatorImpl<DecoderTypModel, DecoderTyp>(() -> new DecoderTyp(), new DecoderTypModelTranscriber()),
              new MutatorImpl<DecoderTyp, DecoderTypModel>(() -> new DecoderTypModel(), new DecoderTypTranscriber())
              );
        this.repository = repository;
        this.adressRepository = adressRepository;
        this.cvRepository = cvRepository;
        this.funktionRepository = funktionRepository;
    }

    public Optional<DecoderTypModel> get(String herstellerStr, String bestellNr) {
        return super.get(() -> repository.findByBestellNr(herstellerStr, bestellNr));
    }

    public Optional<DecoderTypModel> update(String herstellerStr, String bestellNr, DecoderTypModel model) {
        return super.update(() -> repository.findByBestellNr(herstellerStr, bestellNr), model);
    }

    public boolean delete(String herstellerStr, String bestellNr) {
        return super.delete(() -> repository.findByBestellNr(herstellerStr, bestellNr));
    }

    public Optional<DecoderTypAdressModel> getAdress(String herstellerStr, String bestellNr, Integer index) {
        return Optional.empty();
    }

    public Optional<DecoderTypAdressModel> addAdress(String herstellerStr, String bestellNr, DecoderTypAdressModel model) {
        return Optional.empty();
    }

    public Optional<DecoderTypAdressModel> updateAdress(String herstellerStr, String bestellNr, Integer index, DecoderTypAdressModel model) {
        return Optional.empty();
    }

    public boolean deleteAdress(String herstellerStr, String bestellNr, Integer index) {
        return false;
    }

    public Optional<DecoderTypCvModel> getCV(String herstellerStr, String bestellNr, Integer cv) {
        return Optional.empty();
    }

    public Optional<DecoderTypCvModel> addCv(String herstellerStr, String bestellNr, DecoderTypCvModel model) {
        return Optional.empty();
    }

    public Optional<DecoderTypCvModel> updateCv(String herstellerStr, String bestellNr, Integer cv, DecoderTypCvModel model) {
        return Optional.empty();
    }

    public boolean deleteCv(String herstellerStr, String bestellNr, Integer cv) {
        return false;
    }

    public Optional<DecoderTypFunktionModel> getFunktion(String herstellerStr, String bestellNr, Integer reihe, String funktion) {
        return Optional.empty();
    }

    public Optional<DecoderTypFunktionModel> addFunktion(String herstellerStr, String bestellNr, DecoderTypFunktionModel model) {
        return Optional.empty();
    }

    public Optional<DecoderTypFunktionModel> updateFunktion(String herstellerStr, String bestellNr, Integer reihe, String funktion, DecoderTypFunktionModel model) {
        return Optional.empty();
    }

    public boolean deleteFunktion(String herstellerStr, String bestellNr, Integer reihe, String funktion) {
        return false;
    }
}
