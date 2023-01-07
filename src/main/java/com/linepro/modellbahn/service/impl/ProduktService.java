package com.linepro.modellbahn.service.impl;

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */
import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.ProduktMapper;
import com.linepro.modellbahn.converter.entity.ProduktTeilMapper;
import com.linepro.modellbahn.converter.request.ProduktRequestMapper;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.entity.ProduktDecoderTyp;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.repository.DecoderTypRepository;
import com.linepro.modellbahn.repository.ProduktDecoderTypRepository;
import com.linepro.modellbahn.repository.ProduktRepository;
import com.linepro.modellbahn.repository.ProduktTeilRepository;
import com.linepro.modellbahn.repository.lookup.ProduktLookup;
import com.linepro.modellbahn.request.ProduktRequest;

@Service(PREFIX + "ProduktService")

public class ProduktService extends ItemServiceImpl<ProduktModel, ProduktRequest, Produkt> {

    private final ProduktRepository repository;

    private final FileService fileService;

    private final ProduktTeilRepository teilRepository;

    private final ProduktTeilMapper teilMapper;

    private final DecoderTypRepository decoderTypRepository;

    private final ProduktDecoderTypRepository produktDecoderTypRepository;

    @Autowired
    public ProduktService(ProduktRepository repository, ProduktRequestMapper produktRequestMapper, ProduktMapper produktMapper, FileService fileService, 
                    ProduktTeilRepository teilRepository, ProduktTeilMapper teilMapper, ProduktLookup lookup, DecoderTypRepository decoderTypRepository,
                    ProduktDecoderTypRepository produktDecoderTypRepository) {
        super(repository, produktRequestMapper, produktMapper, lookup);
        this.repository = repository;
        this.fileService = fileService;

        this.teilRepository = teilRepository;
        this.teilMapper = teilMapper;
        this.decoderTypRepository = decoderTypRepository;
        this.produktDecoderTypRepository = produktDecoderTypRepository;
    }

    @Override
    public ProduktModel add(ProduktRequest request) {
        Produkt produkt = requestMapper.convert(request);
        produkt.setDeleted(false);

        Set<ProduktDecoderTyp> typen = new HashSet<>(produkt.getDecoderTypen());
        produkt.getDecoderTypen().clear();

        if (typen != null && !typen.isEmpty()) {
            produkt = repository.saveAndFlush(produkt);
            for (ProduktDecoderTyp t : typen) {
                produkt.addDecoderTyp(t.getDecoderTyp());
            }
        }

        return entityMapper.convert(repository.saveAndFlush(produkt));
    }

    public Optional<ProduktModel> get(String hersteller, String bestellNr) {
        return super.get(ProduktModel.builder().hersteller(hersteller).bestellNr(bestellNr).build());
    }

    public Optional<ProduktModel> update(String hersteller, String bestellNr, ProduktRequest request) {
        return super.update(ProduktModel.builder().hersteller(hersteller).bestellNr(bestellNr).build(), request);
    }

    public boolean delete(String hersteller, String bestellNr) {
        return super.delete(ProduktModel.builder().hersteller(hersteller).bestellNr(bestellNr).build());
    }


    @Transactional
    public Optional<ProduktModel> addDecoderTyp(String hersteller, String bestellNr, String decoderTypHersteller, String decoderTypBestellNr) {
        Optional<Produkt> produktFound = repository.findByBestellNr(hersteller, bestellNr);
        Optional<DecoderTyp> decoderTypFound = decoderTypRepository.findByBestellNr(decoderTypHersteller, decoderTypBestellNr);

        if (decoderTypFound.isPresent() && produktFound.isPresent()) {
            DecoderTyp decoderTyp = decoderTypFound.get();
            Produkt produkt = produktFound.get();

             produkt.addDecoderTyp(decoderTyp);

             repository.saveAndFlush(produkt);

             return get(hersteller, bestellNr);
        }

        return Optional.empty();
    }

    @Transactional
    public boolean deleteDecoderTyp(String hersteller, String bestellNr, String typHersteller, String typBestellNr) {
        return produktDecoderTypRepository.findByTeil(hersteller, bestellNr, typHersteller, typBestellNr)
                             .map(t -> {
                                 produktDecoderTypRepository.delete(t);
                                 return true;
                                 })
                             .orElse(false);
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

                 return Optional.of(teilMapper.convert(teil));
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
                                 return teilMapper.convert(teilRepository.saveAndFlush(t));
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
