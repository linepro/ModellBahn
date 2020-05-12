package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.FileService;
import com.linepro.modellbahn.converter.entity.ProduktTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.ProduktModelTranscriber;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.repository.ProduktRepository;
import com.linepro.modellbahn.repository.ProduktTeilRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */

@Service

public class ProduktService extends ItemServiceImpl<ProduktModel,Produkt> implements ItemService<ProduktModel> {

    private final ProduktRepository repository;
    
    private final ProduktTeilRepository teilRepository;
    
    private final FileService fileService;

    @Autowired
    public ProduktService(ProduktRepository repository, ProduktTeilRepository teilRepository, FileService fileService) {
        super(repository, 
                        new MutatorImpl<ProduktModel, Produkt>(() -> new Produkt(), new ProduktModelTranscriber()),
                        new MutatorImpl<Produkt, ProduktModel>(() -> new ProduktModel(), new ProduktTranscriber())
                        );
        this.repository = repository;
        this.teilRepository = teilRepository;
        this.fileService = fileService;
    }

    public Optional<ProduktModel> get(String hersterller, String bestellNr) {
        return super.get(() -> repository.findByBestellNr(hersterller, bestellNr));
    }

    public Optional<ProduktModel> update(String hersterller, String bestellNr, ProduktModel model) {
        return super.update(() -> repository.findByBestellNr(hersterller, bestellNr), model);
    }

    public boolean delete(String hersterller, String bestellNr) {
        return super.delete(() -> repository.findByBestellNr(hersterller, bestellNr));
    }

    public Optional<ProduktTeilModel> addTeil(String hersterller, String bestellNr, ProduktTeilModel teil) {
        return Optional.empty();
    }

    public Optional<ProduktTeilModel> updateTeil(String hersterller, String bestellNr, String teilHerstellerStr, String teilBestellNr, ProduktTeilModel teil) {
        return Optional.empty();
    }

    public boolean deleteTeil(String hersterller, String bestellNr, String teilHerstellerStr, String teilBestellNr) {
        return false;
    }

    @Transactional
    public Optional<ProduktModel> updateAbbildung(String hersterller, String bestellNr, MultipartFile multipart) {
        return  repository.findByBestellNr(hersterller, bestellNr)
                        .map(a -> {
                            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.PRODUKT, ApiNames.ABBILDUNG, hersterller, bestellNr));
                            return entityMutator.convert(a);
                            });
    }

    @Transactional
    public Optional<ProduktModel> deleteAbbildung(String hersterller, String bestellNr) {
        return repository.findByBestellNr(hersterller, bestellNr)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return entityMutator.convert(a);
                            });    }

    @Transactional
    public Optional<ProduktModel> updateAnleitungen(String hersterller, String bestellNr, MultipartFile multipart) {
        return  repository.findByBestellNr(hersterller, bestellNr)
                        .map(a -> {
                            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.PRODUKT, ApiNames.ANDERUNGEN, hersterller, bestellNr));
                            return entityMutator.convert(a);
                            });
    }

    @Transactional
    public Optional<ProduktModel> deleteAnleitungen(String hersterller, String bestellNr) {
        return repository.findByBestellNr(hersterller, bestellNr)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return entityMutator.convert(a);
                            });    }

    @Transactional
    public Optional<ProduktModel> updateExplosionszeichnung(String hersterller, String bestellNr, MultipartFile multipart) {
        return  repository.findByBestellNr(hersterller, bestellNr)
                        .map(a -> {
                            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.PRODUKT, ApiNames.EXPLOSIONSZEICHNUNG, hersterller, bestellNr));
                            return entityMutator.convert(a);
                            });
    }

    @Transactional
    public Optional<ProduktModel> deleteExplosionszeichnung(String hersterller, String bestellNr) {
        return repository.findByBestellNr(hersterller, bestellNr)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return entityMutator.convert(a);
                            });    }
}
