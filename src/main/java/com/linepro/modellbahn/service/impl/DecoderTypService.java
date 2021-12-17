package com.linepro.modellbahn.service.impl;

/**
 * AchsfolgService. CRUD service for DecoderTyp
 * @author $Author:$
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
import com.linepro.modellbahn.converter.entity.DecoderTypCvMapper;
import com.linepro.modellbahn.converter.entity.DecoderTypFunktionMapper;
import com.linepro.modellbahn.converter.entity.DecoderTypMapper;
import com.linepro.modellbahn.converter.request.DecoderTypCvRequestMapper;
import com.linepro.modellbahn.converter.request.DecoderTypFunktionRequestMapper;
import com.linepro.modellbahn.converter.request.DecoderTypRequestMapper;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.repository.DecoderTypCvRepository;
import com.linepro.modellbahn.repository.DecoderTypFunktionRepository;
import com.linepro.modellbahn.repository.DecoderTypRepository;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.request.DecoderTypCvRequest;
import com.linepro.modellbahn.request.DecoderTypFunktionRequest;
import com.linepro.modellbahn.request.DecoderTypRequest;
import com.linepro.modellbahn.service.ItemService;

@Service(PREFIX + "DecoderTypService")
public class DecoderTypService extends ItemServiceImpl<DecoderTypModel, DecoderTypRequest, DecoderTyp> implements ItemService<DecoderTypModel, DecoderTypRequest> {

    private final DecoderTypRepository repository;

    private final FileService fileService;

    private final DecoderTypCvRepository cvRepository;
    private final DecoderTypCvRequestMapper cvRequestMapper;
    private final DecoderTypCvMapper cvMapper;

    private final DecoderTypFunktionRepository funktionRepository;
    private final DecoderTypFunktionRequestMapper funktionRequestMapper;
    private final DecoderTypFunktionMapper funktionMapper;

    @Autowired
    public DecoderTypService(DecoderTypRepository repository, FileService fileService, DecoderTypRequestMapper decoderTypRequestMapper, 
                    DecoderTypMapper decoderTypMapper, DecoderTypCvRepository cvRepository, DecoderTypCvRequestMapper cvRequestMapper,
                    DecoderTypCvMapper cvMapper, DecoderTypFunktionRepository funktionRepository, DecoderTypFunktionRequestMapper funktionRequestMapper,
                    DecoderTypFunktionMapper funktionMapper, DecoderTypLookup lookup) {
        super(repository, decoderTypRequestMapper, decoderTypMapper, lookup);
        this.repository = repository;

        this.fileService = fileService;

        this.cvRepository = cvRepository;
        this.cvRequestMapper = cvRequestMapper;
        this.cvMapper = cvMapper;

        this.funktionRepository = funktionRepository;
        this.funktionRequestMapper= funktionRequestMapper;
        this.funktionMapper = funktionMapper;
    }

    public Optional<DecoderTypModel> get(String hersteller, String bestellNr) {
        return super.get(DecoderTypModel.builder().hersteller(hersteller).bestellNr(bestellNr).build());
    }

    public Optional<DecoderTypModel> update(String hersteller, String bestellNr, DecoderTypRequest model) {
        return super.update(DecoderTypModel.builder().hersteller(hersteller).bestellNr(bestellNr).build(), model);
    }

    public boolean delete(String hersteller, String bestellNr) {
        return super.delete(DecoderTypModel.builder().hersteller(hersteller).bestellNr(bestellNr).build());
    }

    @Transactional(readOnly = true)
    public Optional<DecoderTypCvModel> getCV(String hersteller, String bestellNr, Integer cv) {
        return cvRepository.findByCv(hersteller, bestellNr, cv)
                           .map(c -> cvMapper.convert(c));
    }

    @Transactional
    public Optional<DecoderTypCvModel> addCv(String hersteller, String bestellNr, DecoderTypCvRequest request) {
        return repository.findByBestellNr(hersteller, bestellNr)
                         .map(d -> {
                             DecoderTypCv cv = cvRequestMapper.convert(request);
                             d.addCv(cv);
                             repository.saveAndFlush(d);
                             return cvMapper.convert(cv);
                         });
    }

    @Transactional
    public Optional<DecoderTypCvModel> updateCv(String hersteller, String bestellNr, Integer cv, DecoderTypCvRequest request) {
        return cvRepository.findByCv(hersteller, bestellNr, cv)
                           .map(c -> {
                               Boolean deleted = c.getDeleted();
                               DecoderTypCv item = cvRequestMapper.apply(request, c);
                               item.setDeleted(deleted);
                               return cvMapper.convert(cvRepository.saveAndFlush(item));
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
    public Optional<DecoderTypFunktionModel> getFunktion(String hersteller, String bestellNr, String funktion) {
        return funktionRepository.findByFunktion(hersteller, bestellNr, funktion)
                                 .map(f -> funktionMapper.convert(f));
    }

    @Transactional
    public Optional<DecoderTypFunktionModel> addFunktion(String hersteller, String bestellNr, DecoderTypFunktionRequest request) {
        return repository.findByBestellNr(hersteller, bestellNr)
                         .map(d -> {
                             DecoderTypFunktion funktion = funktionRequestMapper.convert(request);
                             d.addFunktion(funktion);
                             repository.saveAndFlush(d);
                             return funktionMapper.convert(funktion);
                         });
    }

    @Transactional
    public Optional<DecoderTypFunktionModel> updateFunktion(String hersteller, String bestellNr, String funktion, DecoderTypFunktionRequest request) {
        return funktionRepository.findByFunktion(hersteller, bestellNr, funktion)
                                 .map(f -> {
                                     Boolean deleted = f.getDeleted();
                                     DecoderTypFunktion fn = funktionRequestMapper.apply(request, f);
                                     fn.setDeleted(deleted);
                                     return funktionMapper.convert(funktionRepository.saveAndFlush(fn));
                                 });
    }

    @Transactional
    public boolean deleteFunktion(String hersteller, String bestellNr, String funktion) {
        return funktionRepository.findByFunktion(hersteller, bestellNr, funktion)
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
