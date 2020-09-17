package com.linepro.modellbahn.service.impl;

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */
import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.ProduktMutator;
import com.linepro.modellbahn.converter.entity.ProduktTeilMutator;
import com.linepro.modellbahn.converter.model.ProduktModelMutator;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.repository.ProduktRepository;
import com.linepro.modellbahn.repository.ProduktTeilRepository;
import com.linepro.modellbahn.service.ItemService;
import com.linepro.modellbahn.service.criterion.ProduktCriterion;

@Service(PREFIX + "ProduktService")

public class ProduktService extends ItemServiceImpl<ProduktModel,Produkt> implements ItemService<ProduktModel> {

    private final ProduktRepository repository;

    private final FileService fileService;

    private final ProduktTeilRepository teilRepository;

    private final ProduktTeilMutator teilMutator;

    @Autowired
    public ProduktService(ProduktRepository repository, ProduktModelMutator produktModelMutator, ProduktMutator produktMutator, FileService fileService, 
                    ProduktTeilRepository teilRepository, ProduktTeilMutator teilMutator) {
        super(repository, produktModelMutator, produktMutator);
        this.repository = repository;
        this.fileService = fileService;

        this.teilRepository = teilRepository;
        this.teilMutator = teilMutator;
    }

    @Override
    protected Page<Produkt> findAll(Optional<ProduktModel> model, Pageable pageRequest) {
        return repository.findAll(new ProduktCriterion(model), pageRequest);
    }

    public Optional<ProduktModel> get(String hersteller, String bestellNr) {
        return super.get(() -> repository.findByBestellNr(hersteller, bestellNr));
    }

    public Optional<ProduktModel> update(String hersteller, String bestellNr, ProduktModel model) {
        return super.update(() -> repository.findByBestellNr(hersteller, bestellNr), model);
    }

    public boolean delete(String hersteller, String bestellNr) {
        return super.delete(() -> repository.findByBestellNr(hersteller, bestellNr));
    }

    @Transactional
    public Optional<ProduktTeilModel> addTeil(String hersteller, String bestellNr, String teilHersteller, String teilBestellNr, Integer anzahl) {
        Optional<Produkt> pt = repository.findByBestellNr(teilHersteller, teilBestellNr);

        if (pt.isPresent()) {
            return repository.findByBestellNr(hersteller, bestellNr)
                             .map(p -> {
                                 ProduktTeil teil = new ProduktTeil();
                                 teil.setTeil(pt.get());
                                 teil.setAnzahl(anzahl);
                                 teil.setDeleted(false);

                                 p.addTeil(teil);

                                 repository.saveAndFlush(p);

                                 return teilMutator.convert(teil);
                                 });
        }

        return Optional.empty();
    }

    @Transactional
    public Optional<ProduktTeilModel> updateTeil(String hersteller, String bestellNr, String teilHersteller, String teilBestellNr, Integer anzahl) {
        return teilRepository.findByTeil(hersteller, bestellNr, teilHersteller, teilBestellNr)
                             .map(t -> {
                                 t.setAnzahl(anzahl);
                                 return teilMutator.convert(teilRepository.saveAndFlush(t));
                             });
    }

    @Transactional
    public boolean deleteTeil(String hersteller, String bestellNr, String teilHersteller, String teilBestellNr) {
        return teilRepository.findByTeil(hersteller, bestellNr, teilHersteller, teilBestellNr)
                             .map(t -> {
                                 teilRepository.delete(t);
                                 return true;
                             })
                             .orElse(false);
    }

    @Transactional
    public Optional<ProduktModel> updateAbbildung(String hersteller, String bestellNr, MultipartFile multipart) {
        return  repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.PRODUKT, ApiNames.ABBILDUNG, hersteller, bestellNr));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }

    @Transactional
    public Optional<ProduktModel> deleteAbbildung(String hersteller, String bestellNr) {
        return repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }

    @Transactional
    public Optional<ProduktModel> updateAnleitungen(String hersteller, String bestellNr, MultipartFile multipart) {
        return  repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setAnleitungen(fileService.updateFile(AcceptableMediaTypes.DOCUMENT_TYPES, multipart, ApiNames.PRODUKT, ApiNames.ANLEITUNGEN, hersteller, bestellNr));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }

    @Transactional
    public Optional<ProduktModel> deleteAnleitungen(String hersteller, String bestellNr) {
        return repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setAnleitungen(fileService.deleteFile(a.getAnleitungen()));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }

    @Transactional
    public Optional<ProduktModel> updateExplosionszeichnung(String hersteller, String bestellNr, MultipartFile multipart) {
        return  repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setExplosionszeichnung(fileService.updateFile(AcceptableMediaTypes.DOCUMENT_TYPES, multipart, ApiNames.PRODUKT, ApiNames.EXPLOSIONSZEICHNUNG, hersteller, bestellNr));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }

    @Transactional
    public Optional<ProduktModel> deleteExplosionszeichnung(String hersteller, String bestellNr) {
        return repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setExplosionszeichnung(fileService.deleteFile(a.getExplosionszeichnung()));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }
}
