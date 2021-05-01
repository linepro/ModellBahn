package com.linepro.modellbahn.service.impl;

/**
 * DecoderService. CRUD service for Decoder
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

import com.linepro.modellbahn.converter.entity.DecoderAdressMutator;
import com.linepro.modellbahn.converter.entity.DecoderCvMutator;
import com.linepro.modellbahn.converter.entity.DecoderFunktionMutator;
import com.linepro.modellbahn.converter.entity.DecoderMutator;
import com.linepro.modellbahn.converter.model.DecoderModelMutator;
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
import com.linepro.modellbahn.service.criterion.DecoderCriterion;

@Service(PREFIX + "DecoderService")
public class DecoderService extends ItemServiceImpl<DecoderModel, Decoder> {

    private final DecoderRepository repository;

    private final DecoderTypRepository typRepository;

    private final DecoderAdressRepository adressRepository;

    private final DecoderAdressMutator adressMutator;

    private final DecoderCvRepository cvRepository;

    private final DecoderCvMutator cvMutator;

    private final DecoderFunktionRepository funktionRepository;

    private final DecoderFunktionMutator funktionMutator;

    private final AssetIdGenerator assetIdGenerator;

    @Autowired
    public DecoderService(DecoderRepository repository, DecoderTypRepository typRepository, DecoderModelMutator decoderModelMutator,
                    DecoderMutator decoderMutator, DecoderAdressRepository adressRepository, DecoderAdressMutator adressMutator,
                    DecoderCvRepository cvRepository, DecoderCvMutator cvMutator, DecoderFunktionRepository funktionRepository,
                    DecoderFunktionMutator funktionMutator, AssetIdGenerator assetIdGenerator) {
        super(repository, decoderModelMutator, decoderMutator);
        this.repository = repository;

        this.typRepository = typRepository;

        this.adressRepository = adressRepository;
        this.adressMutator = adressMutator;

        this.cvRepository = cvRepository;
        this.cvMutator = cvMutator;

        this.funktionRepository = funktionRepository;
        this.funktionMutator = funktionMutator;

        this.assetIdGenerator = assetIdGenerator;
    }

    @Transactional
    public Optional<DecoderModel> add(String herstellerStr, String bestellNr, DecoderModel model) {
        Optional<DecoderTyp> found = typRepository.findByBestellNr(herstellerStr, bestellNr);

        if (found.isPresent()) {
            DecoderTyp decoderTyp = found.get();

            Decoder initial = new Decoder();
            model.setDecoderId(null);
            initial.setDecoderId(assetIdGenerator.getNextAssetId());
            initial.setDecoderTyp(decoderTyp);
            initial = modelMutator.apply(model, initial);
            initial.setDeleted(false);

            final Decoder decoder = repository.saveAndFlush(initial);

            decoderTyp.getAdressen().forEach(a -> decoder.addAdress(DecoderAdress.builder().typ(a).adress(a.getAdress()).deleted(false).build()));

            decoderTyp.getCvs().forEach(c -> decoder.addCv(DecoderCv.builder().cv(c).wert(c.getWerkseinstellung()).deleted(false).build()));

            decoderTyp.getFunktionen().forEach(
                            f -> decoder.addFunktion(DecoderFunktion.builder().funktion(f).bezeichnung(f.getBezeichnung()).deleted(false).build()));

            return Optional.of(entityMutator.convert(repository.saveAndFlush(decoder)));
        }

        return Optional.empty();
    }

    @Override
    protected Page<Decoder> findAll(Optional<DecoderModel> model, Pageable pageRequest) {
        return repository.findAll(new DecoderCriterion(model), pageRequest);
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

    @Transactional
    public Optional<DecoderAdressModel> updateAdress(String decoderId, Integer index, Integer adress) {
        return adressRepository.findByIndex(decoderId, index)
                        .map(a -> { a.setAdress(adress); return adressMutator.convert(adressRepository.saveAndFlush(a)); });
    }

    @Transactional
    public Optional<DecoderCvModel> updateCv(String decoderId, Integer cv, Integer wert) {
        return cvRepository.findByCv(decoderId, cv).map(c -> { c.setWert(wert); return cvMutator.convert(cvRepository.saveAndFlush(c)); });
    }

    @Transactional
    public Optional<DecoderFunktionModel> updateFunktion(String decoderId, Integer reihe, String funktion, String bezeichnung) {
        return funktionRepository.findByFunktion(decoderId, reihe, funktion)
                        .map(f -> { f.setBezeichnung(bezeichnung); return funktionMutator.convert(funktionRepository.saveAndFlush(f)); });
    }
}
