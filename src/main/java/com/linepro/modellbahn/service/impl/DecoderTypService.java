package com.linepro.modellbahn.service.impl;

/**
 * AchsfolgService. CRUD service for DecoderTyp
 * @author $Author:$
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
import com.linepro.modellbahn.service.criterion.DecoderTypCriterion;

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

    @Override
    protected Page<DecoderTyp> findAll(Optional<DecoderTypModel> model, Pageable pageRequest) {
        return repository.findAll(new DecoderTypCriterion(model), pageRequest);
    }

    public Optional<DecoderTypModel> get(String hersteller, String bestellNr) {
        return super.get(() -> repository.findByBestellNr(hersteller, bestellNr));
    }

    public Optional<DecoderTypModel> update(String hersteller, String bestellNr, DecoderTypModel model) {
        return super.update(() -> repository.findByBestellNr(hersteller, bestellNr), model);
    }

    public boolean delete(String hersteller, String bestellNr) {
        return super.delete(() -> repository.findByBestellNr(hersteller, bestellNr));
    }

    @Transactional(readOnly = true)
    public Optional<DecoderTypAdressModel> getAdress(String hersteller, String bestellNr, Integer index) {
        return adressRepository.findByIndex(hersteller, bestellNr, index)
                               .map(a -> adressMutator.convert(a));
    }

    @Transactional
    public Optional<DecoderTypAdressModel> addAdress(String hersteller, String bestellNr, DecoderTypAdressModel model) {
        return repository.findByBestellNr(hersteller, bestellNr)
                         .map(d -> {
                             DecoderTypAdress adress = adressModelMutator.convert(model);
                             d.addAdress(adress);
                             repository.saveAndFlush(d);
                             return adressMutator.convert(adress);
                         });
    }

    @Transactional
    public Optional<DecoderTypAdressModel> updateAdress(String hersteller, String bestellNr, Integer index, DecoderTypAdressModel model) {
        return adressRepository.findByIndex(hersteller, bestellNr, index)
                               .map(a -> {
                                   Boolean deleted = a.getDeleted();
                                   DecoderTypAdress adress = adressModelMutator.apply(model, a);
                                   adress.setDeleted(deleted);
                                   return adressMutator.convert(adressRepository.saveAndFlush(adress));
                               });
    }

    @Transactional
    public boolean deleteAdress(String hersteller, String bestellNr, Integer index) {
       return adressRepository.findByIndex(hersteller, bestellNr, index)
                               .map(a -> {
                                   adressRepository.delete(a);
                                   return true;
                               })
                               .orElse(false);
    }

    @Transactional(readOnly = true)
    public Optional<DecoderTypCvModel> getCV(String hersteller, String bestellNr, Integer cv) {
        return cvRepository.findByCv(hersteller, bestellNr, cv)
                           .map(c -> cvMutator.convert(c));
    }

    @Transactional
    public Optional<DecoderTypCvModel> addCv(String hersteller, String bestellNr, DecoderTypCvModel model) {
        return repository.findByBestellNr(hersteller, bestellNr)
                         .map(d -> {
                             DecoderTypCv cv = cvModelMutator.convert(model);
                             d.addCv(cv);
                             repository.saveAndFlush(d);
                             return cvMutator.convert(cv);
                         });
    }

    @Transactional
    public Optional<DecoderTypCvModel> updateCv(String hersteller, String bestellNr, Integer cv, DecoderTypCvModel model) {
        return cvRepository.findByCv(hersteller, bestellNr, cv)
                           .map(c -> {
                               Boolean deleted = c.getDeleted();
                               DecoderTypCv item = cvModelMutator.apply(model, c);
                               item.setDeleted(deleted);
                               return cvMutator.convert(cvRepository.saveAndFlush(item));
                           });
    }

    @Transactional
    public boolean deleteCv(String hersteller, String bestellNr, Integer cv) {
        return cvRepository.findByCv(hersteller, bestellNr, cv)
                           .map(c -> {
                               cvRepository.delete(c);
                               return true;
                           })
                           .orElse(false);
    }

    @Transactional(readOnly = true)
    public Optional<DecoderTypFunktionModel> getFunktion(String hersteller, String bestellNr, Integer reihe, String funktion) {
        return funktionRepository.findByFunktion(hersteller, bestellNr, reihe, funktion)
                                 .map(f -> funktionMutator.convert(f));
    }

    @Transactional
    public Optional<DecoderTypFunktionModel> addFunktion(String hersteller, String bestellNr, DecoderTypFunktionModel model) {
        return repository.findByBestellNr(hersteller, bestellNr)
                         .map(d -> {
                             DecoderTypFunktion funktion = funktionModelMutator.convert(model);
                             d.addFunktion(funktion);
                             repository.saveAndFlush(d);
                             return funktionMutator.convert(funktion);
                         });
    }

    @Transactional
    public Optional<DecoderTypFunktionModel> updateFunktion(String hersteller, String bestellNr, Integer reihe, String funktion, DecoderTypFunktionModel model) {
        return funktionRepository.findByFunktion(hersteller, bestellNr, reihe, funktion)
                                 .map(f -> {
                                     Boolean deleted = f.getDeleted();
                                     DecoderTypFunktion fn = funktionModelMutator.apply(model, f);
                                     fn.setDeleted(deleted);
                                     return funktionMutator.convert(funktionRepository.saveAndFlush(fn));
                                 });
    }

    @Transactional
    public boolean deleteFunktion(String hersteller, String bestellNr, Integer reihe, String funktion) {
        return funktionRepository.findByFunktion(hersteller, bestellNr, reihe, funktion)
                                 .map(f -> {
                                     funktionRepository.delete(f);
                                     return true;
                                 })
                                 .orElse(false);
    }

    @Transactional
    public Optional<DecoderTypModel> updateAnleitungen(String hersteller, String bestellNr, MultipartFile multipart) {
        return  repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setAnleitungen(fileService.updateFile(AcceptableMediaTypes.DOCUMENT_TYPES, multipart, ApiNames.DECODER_TYP, ApiNames.ANLEITUNGEN, hersteller, bestellNr));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }

    @Transactional
    public Optional<DecoderTypModel> deleteAnleitungen(String hersteller, String bestellNr) {
        return repository.findByBestellNr(hersteller, bestellNr)
                        .map(a -> {
                            a.setAnleitungen(fileService.deleteFile(a.getAnleitungen()));
                            return repository.saveAndFlush(a);
                        })
                        .flatMap(e -> this.get(hersteller, bestellNr));
    }
}
