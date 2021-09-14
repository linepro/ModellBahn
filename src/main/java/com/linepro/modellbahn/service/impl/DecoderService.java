package com.linepro.modellbahn.service.impl;

/**
 * DecoderService. CRUD service for Decoder
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.entity.DecoderAdressMapper;
import com.linepro.modellbahn.converter.entity.DecoderCvMapper;
import com.linepro.modellbahn.converter.entity.DecoderFunktionMapper;
import com.linepro.modellbahn.converter.entity.DecoderMapper;
import com.linepro.modellbahn.converter.request.DecoderRequestMapper;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.persistence.util.AssetIdGenerator;
import com.linepro.modellbahn.repository.DecoderAdressRepository;
import com.linepro.modellbahn.repository.DecoderCvRepository;
import com.linepro.modellbahn.repository.DecoderFunktionRepository;
import com.linepro.modellbahn.repository.DecoderRepository;
import com.linepro.modellbahn.repository.DecoderTypRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.request.DecoderRequest;

@Service(PREFIX + "DecoderService")
public class DecoderService extends ItemServiceImpl<DecoderModel, DecoderRequest,  Decoder> {

    private final DecoderRepository repository;

    private final DecoderTypRepository typRepository;

    private final DecoderAdressRepository adressRepository;

    private final DecoderAdressMapper adressMapper;

    private final DecoderCvRepository cvRepository;

    private final DecoderCvMapper cvMapper;

    private final DecoderFunktionRepository funktionRepository;

    private final DecoderFunktionMapper funktionMapper;

    private final AssetIdGenerator assetIdGenerator;

    @Autowired
    public DecoderService(DecoderRepository repository, DecoderTypRepository typRepository, DecoderRequestMapper decoderRequestMapper,
                    DecoderMapper decoderMapper, DecoderAdressRepository adressRepository, DecoderAdressMapper adressMapper,
                    DecoderCvRepository cvRepository, DecoderCvMapper cvMapper, DecoderFunktionRepository funktionRepository,
                    DecoderFunktionMapper funktionMapper, AssetIdGenerator assetIdGenerator, DecoderLookup decoderLookup) {
        super(repository, decoderRequestMapper, decoderMapper, decoderLookup);
        this.repository = repository;

        this.typRepository = typRepository;

        this.adressRepository = adressRepository;
        this.adressMapper = adressMapper;

        this.cvRepository = cvRepository;
        this.cvMapper = cvMapper;

        this.funktionRepository = funktionRepository;
        this.funktionMapper = funktionMapper;

        this.assetIdGenerator = assetIdGenerator;
    }

    @Transactional
    public Optional<DecoderModel> add(String herstellerStr, String bestellNr, DecoderRequest request) {
        Optional<DecoderTyp> found = typRepository.findByBestellNr(herstellerStr, bestellNr);

        if (found.isPresent()) {
            DecoderTyp decoderTyp = found.get();

            Decoder initial = new Decoder();
            request.setDecoderId(null);
            initial.setDecoderId(assetIdGenerator.getNextAssetId());
            initial.setDecoderTyp(decoderTyp);
            initial = requestMapper.apply(request, initial);
            initial.setDeleted(false);

            final Decoder decoder = repository.saveAndFlush(initial);

            decoderTyp.getAdressen().forEach(a -> decoder.addAdress(DecoderAdress.builder().typ(a).adress(a.getAdress()).deleted(false).build()));

            decoderTyp.getCvs().forEach(c -> decoder.addCv(DecoderCv.builder().cv(c).wert(c.getWerkseinstellung()).deleted(false).build()));

            decoderTyp.getFunktionen().forEach(
                            f -> decoder.addFunktion(DecoderFunktion.builder().funktion(f).bezeichnung(f.getBezeichnung()).deleted(false).build()));

            return Optional.of(entityMapper.convert(repository.saveAndFlush(decoder)));
        }

        return Optional.empty();
    }

    public Optional<DecoderModel> get(String decoderId) {
        return super.get(DecoderModel.builder().decoderId(decoderId).build());
    }

    public Optional<DecoderModel> update(String decoderId, DecoderRequest request) {
        return super.update(DecoderModel.builder().decoderId(decoderId).build(), request);
    }

    public boolean delete(String decoderId) {
        return super.delete(DecoderModel.builder().decoderId(decoderId).build());
    }

    @Transactional
    public Optional<DecoderAdressModel> updateAdress(String decoderId, Integer index, Integer adress) {
        return adressRepository.findByIndex(decoderId, index)
                               .map(a -> {
                                   a.setAdress(adress);
                                   return adressMapper.convert(adressRepository.saveAndFlush(a));
                                   });
    }

    @Transactional
    public Optional<DecoderCvModel> updateCv(String decoderId, Integer cv, Integer wert) {
        return cvRepository.findByCv(decoderId, cv)
                           .map(c -> {
                               c.setWert(wert); return cvMapper.convert(cvRepository.saveAndFlush(c));
                               });
    }

    @Transactional
    public Optional<DecoderFunktionModel> updateFunktion(String decoderId, Integer reihe, String funktion, String bezeichnung) {
        return funktionRepository.findByFunktion(decoderId, reihe, funktion)
                                 .map(f -> {
                                     f.setBezeichnung(bezeichnung); return funktionMapper.convert(funktionRepository.saveAndFlush(f));
                                     });
    }
}
