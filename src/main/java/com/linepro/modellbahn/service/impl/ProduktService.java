package com.linepro.modellbahn.service.impl;

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */
import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.ProduktMutator;
import com.linepro.modellbahn.converter.entity.ProduktTeilMutator;
import com.linepro.modellbahn.converter.request.ProduktRequestMapper;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.repository.ProduktRepository;
import com.linepro.modellbahn.repository.ProduktTeilRepository;
import com.linepro.modellbahn.request.ProduktRequest;

@Service(PREFIX + "ProduktService")

public class ProduktService extends ItemServiceImpl<ProduktModel, ProduktRequest, Produkt> {

    private final ProduktRepository repository;

    private final FileService fileService;

    private final ProduktTeilRepository teilRepository;

    private final ProduktTeilMutator teilMutator;

    @Autowired
    public ProduktService(ProduktRepository repository, ProduktRequestMapper produktRequestMapper, ProduktMutator produktMutator, FileService fileService, 
                    ProduktTeilRepository teilRepository, ProduktTeilMutator teilMutator) {
        super(repository, produktRequestMapper, produktMutator);
        this.repository = repository;
        this.fileService = fileService;

        this.teilRepository = teilRepository;
        this.teilMutator = teilMutator;
    }

    public Optional<ProduktModel> get(String hersteller, String bestellNr) {
        return super.get(() -> repository.findByBestellNr(hersteller, bestellNr));
    }

    public Optional<ProduktModel> update(String hersteller, String bestellNr, ProduktRequest request) {
        return super.update(() -> repository.findByBestellNr(hersteller, bestellNr), request);
    }

    public boolean delete(String hersteller, String bestellNr) {
        return super.delete(() -> repository.findByBestellNr(hersteller, bestellNr));
    }

    @Transactional
    public Optional<ProduktTeilModel> addTeil(String hersteller, String bestellNr, String teilHersteller, String teilBestellNr, Integer menge) {
        Optional<Produkt> produktFound = repository.findByBestellNr(hersteller, bestellNr);
        Optional<Produkt> subFound = repository.findByBestellNr(teilHersteller, teilBestellNr);

        if (subFound.isPresent() && produktFound.isPresent()) {
            Produkt sub = subFound.get();
            Produkt produkt = produktFound.get();

            if (!isCycle(produkt, sub)) {
                 ProduktTeil teil = new ProduktTeil();
                 teil.setTeil(sub);
                 teil.setMenge(menge);
                 teil.setDeleted(false);

                 produkt.addTeil(teil);

                 repository.saveAndFlush(produkt);

                 return Optional.of(teilMutator.convert(teil));
            }
        }

        return Optional.empty();
    }

    protected boolean isCycle(Produkt produkt, Produkt sub) {
        return !teilRepository.findTeilen(produkt.getId(), sub.getId()).isEmpty();
    }

    @Transactional
    public Optional<ProduktTeilModel> updateTeil(String hersteller, String bestellNr, String teilHersteller, String teilBestellNr, Integer menge) {
        return teilRepository.findByTeil(hersteller, bestellNr, teilHersteller, teilBestellNr)
                             .map(t -> {
                                 t.setMenge(menge);
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

    @Transactional
    public Optional<ProduktModel> updateGrossansicht(String hersteller, String bestellNr, MultipartFile multipart) {
        return  repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setGrossansicht(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.PRODUKT, ApiNames.GROSSANSICHT, hersteller, bestellNr));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }

    @Transactional
    public Optional<ProduktModel> deleteGrossansicht(String hersteller, String bestellNr) {
        return repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setGrossansicht(fileService.deleteFile(a.getGrossansicht()));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }
}
