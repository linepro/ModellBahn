package com.linepro.modellbahn.service.impl;

/**
 * AchsfolgService. CRUD service for DecoderTyp
 * @author $Author:$
 * @version $Id:$
 */
import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.DecoderTypAdressMutator;
import com.linepro.modellbahn.converter.entity.DecoderTypCvMutator;
import com.linepro.modellbahn.converter.entity.DecoderTypFunktionMutator;
import com.linepro.modellbahn.converter.entity.DecoderTypMutator;
import com.linepro.modellbahn.converter.model.DecoderTypAdressModelMutator;
import com.linepro.modellbahn.converter.model.DecoderTypCvModelMutator;
import com.linepro.modellbahn.converter.model.DecoderTypFunktionModelMutator;
import com.linepro.modellbahn.converter.model.DecoderTypModelMutator;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.repository.DecoderTypAdressRepository;
import com.linepro.modellbahn.repository.DecoderTypCvRepository;
import com.linepro.modellbahn.repository.DecoderTypFunktionRepository;
import com.linepro.modellbahn.repository.DecoderTypRepository;
import com.linepro.modellbahn.service.ItemService;

@Service(PREFIX + "DecoderTypService")
public class DecoderTypService extends ItemServiceImpl<DecoderTypModel,DecoderTyp> implements ItemService<DecoderTypModel> {

    private final DecoderTypRepository repository;
    
    private final FileService fileService;
    
    private final DecoderTypAdressRepository adressRepository;
    private final DecoderTypAdressModelMutator adressModelMutator;
    private final DecoderTypAdressMutator adressMutator;

    private final DecoderTypCvRepository cvRepository;
    private final DecoderTypCvModelMutator cvModelMutator;
    private final DecoderTypCvMutator cvMutator;

    private final DecoderTypFunktionRepository funktionRepository;
    private final DecoderTypFunktionModelMutator funktionModelMutator;
    private final DecoderTypFunktionMutator funktionMutator;

    @Autowired
    public DecoderTypService(DecoderTypRepository repository, FileService fileService, DecoderTypModelMutator decoderTypModelMutator, 
                    DecoderTypMutator decoderTypMutator, DecoderTypAdressRepository adressRepository, DecoderTypAdressModelMutator adressModelMutator, 
                    DecoderTypAdressMutator adressMutator, DecoderTypCvRepository cvRepository, DecoderTypCvModelMutator cvModelMutator, DecoderTypCvMutator cvMutator,
                    DecoderTypFunktionRepository funktionRepository,  DecoderTypFunktionModelMutator funktionModelMutator, DecoderTypFunktionMutator funktionMutator) {
        super(repository, decoderTypModelMutator, decoderTypMutator);
        this.repository = repository;

        this.fileService = fileService;

        this.adressRepository = adressRepository;
        this.adressModelMutator = adressModelMutator;
        this.adressMutator = adressMutator;

        this.cvRepository = cvRepository;
        this.cvModelMutator = cvModelMutator;
        this.cvMutator = cvMutator;

        this.funktionRepository = funktionRepository;
        this.funktionModelMutator= funktionModelMutator;
        this.funktionMutator = funktionMutator;
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

    @Transactional(readOnly = true)
    public Optional<DecoderTypAdressModel> getAdress(String herstellerStr, String bestellNr, Integer index) {
        return adressRepository.findByIndex(herstellerStr, bestellNr, index)
                               .map(a -> adressMutator.convert(a));
    }

    @Transactional
    public Optional<DecoderTypAdressModel> addAdress(String herstellerStr, String bestellNr, DecoderTypAdressModel model) {
        return repository.findByBestellNr(herstellerStr, bestellNr)
                         .map(d -> {
                             DecoderTypAdress adress = adressModelMutator.convert(model);
                             d.addAdress(adress);
                             repository.saveAndFlush(d);
                             return adressMutator.convert(adress);
                         });
    }

    @Transactional
    public Optional<DecoderTypAdressModel> updateAdress(String herstellerStr, String bestellNr, Integer index, DecoderTypAdressModel model) {
        return adressRepository.findByIndex(herstellerStr, bestellNr, index)
                               .map(a -> adressMutator.convert(adressRepository.saveAndFlush(adressModelMutator.convert(model))));
    }

    @Transactional
    public boolean deleteAdress(String herstellerStr, String bestellNr, Integer index) {
       return adressRepository.findByIndex(herstellerStr, bestellNr, index)
                               .map(a -> {
                                   adressRepository.delete(a);
                                   return true;
                               })
                               .orElse(false);
    }

    @Transactional(readOnly = true)
    public Optional<DecoderTypCvModel> getCV(String herstellerStr, String bestellNr, Integer cv) {
        return cvRepository.findByCv(herstellerStr, bestellNr, cv)
                           .map(c -> cvMutator.convert(c));
    }

    @Transactional
    public Optional<DecoderTypCvModel> addCv(String herstellerStr, String bestellNr, DecoderTypCvModel model) {
        return repository.findByBestellNr(herstellerStr, bestellNr)
                         .map(d -> {
                             DecoderTypCv cv = cvModelMutator.convert(model);
                             d.addCv(cv);
                             repository.saveAndFlush(d);
                             return cvMutator.convert(cv);
                         });
    }

    @Transactional
    public Optional<DecoderTypCvModel> updateCv(String herstellerStr, String bestellNr, Integer cv, DecoderTypCvModel model) {
        return cvRepository.findByCv(herstellerStr, bestellNr, cv)
                           .map(a -> cvMutator.convert(cvRepository.saveAndFlush(cvModelMutator.convert(model))));
    }

    @Transactional
    public boolean deleteCv(String herstellerStr, String bestellNr, Integer cv) {
        return cvRepository.findByCv(herstellerStr, bestellNr, cv)
                           .map(c -> {
                               cvRepository.delete(c);
                               return true;
                           })
                           .orElse(false);
    }

    @Transactional(readOnly = true)
    public Optional<DecoderTypFunktionModel> getFunktion(String herstellerStr, String bestellNr, Integer reihe, String funktion) {
        return funktionRepository.findByFunktion(herstellerStr, bestellNr, reihe, funktion)
                                 .map(f -> funktionMutator.convert(f));
    }

    @Transactional
    public Optional<DecoderTypFunktionModel> addFunktion(String herstellerStr, String bestellNr, DecoderTypFunktionModel model) {
        return repository.findByBestellNr(herstellerStr, bestellNr)
                         .map(d -> {
                             DecoderTypFunktion funktion = funktionModelMutator.convert(model);
                             d.addFunktion(funktion);
                             repository.saveAndFlush(d);
                             return funktionMutator.convert(funktion);
                         });
    }

    @Transactional
    public Optional<DecoderTypFunktionModel> updateFunktion(String herstellerStr, String bestellNr, Integer reihe, String funktion, DecoderTypFunktionModel model) {
        return funktionRepository.findByFunktion(herstellerStr, bestellNr, reihe, funktion)
                                 .map(a -> funktionMutator.convert(funktionRepository.saveAndFlush(funktionModelMutator.convert(model))));
    }

    @Transactional
    public boolean deleteFunktion(String herstellerStr, String bestellNr, Integer reihe, String funktion) {
        return funktionRepository.findByFunktion(herstellerStr, bestellNr, reihe, funktion)
                                 .map(f -> {
                                     funktionRepository.delete(f);
                                     return true;
                                 })
                                 .orElse(false);
    }

    @Transactional
    public Optional<DecoderTypModel> updateAnleitungen(String herstellerStr, String bestellNr, MultipartFile multipart) {
        return  repository.findByBestellNr(herstellerStr, bestellNr)
                        .map(a -> {
                            a.setAnleitungen(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.DECODER_TYP, ApiNames.ANLEITUNGEN, herstellerStr, bestellNr));
                            return entityMutator.convert(a);
                        });
    }

    @Transactional
    public Optional<DecoderTypModel> deleteAnleitungen(String herstellerStr, String bestellNr) {
        return repository.findByBestellNr(herstellerStr, bestellNr)
                        .map(a -> {
                            a.setAnleitungen(fileService.deleteFile(a.getAnleitungen()));
                            return entityMutator.convert(a);
                        });
    }
}
