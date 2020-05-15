package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.linepro.modellbahn.model.enums.DecoderStatus;
import com.linepro.modellbahn.repository.DecoderAdressRepository;
import com.linepro.modellbahn.repository.DecoderCvRepository;
import com.linepro.modellbahn.repository.DecoderFunktionRepository;
import com.linepro.modellbahn.repository.DecoderRepository;
import com.linepro.modellbahn.repository.DecoderTypRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * DecoderService. CRUD service for Decoder
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class DecoderService extends ItemServiceImpl<DecoderModel,Decoder> implements ItemService<DecoderModel> {

    private final DecoderRepository repository;
    
    private final DecoderTypRepository typRepository;
    
    private final DecoderAdressRepository adressRepository;
    private final DecoderAdressMutator adressMutator;

    private final DecoderCvRepository cvRepository;
    private final DecoderCvMutator cvMutator;

    private final DecoderFunktionRepository funktionRepository;
    private final DecoderFunktionMutator funktionMutator;

    @Autowired
    public DecoderService(DecoderRepository repository, DecoderTypRepository typRepository, DecoderModelMutator decoderModelMutator, DecoderMutator decoderMutator,
                    DecoderAdressRepository adressRepository, DecoderAdressMutator adressMutator, DecoderCvRepository cvRepository, DecoderCvMutator cvMutator,
                    DecoderFunktionRepository funktionRepository, DecoderFunktionMutator funktionMutator) {
        super(repository, decoderModelMutator, decoderMutator);
        this.repository = repository;

        this.typRepository = typRepository;
        
        this.adressRepository = adressRepository;
        this.adressMutator = adressMutator;

        this.cvRepository = cvRepository;
        this.cvMutator = cvMutator;

        this.funktionRepository = funktionRepository;
        this.funktionMutator = funktionMutator;
    }

    @Transactional
    public Optional<DecoderModel> add(String herstellerStr, String bestellNr) {
        Optional<DecoderTyp> found = typRepository.findByBestellNr(herstellerStr, bestellNr);
        
        if (found.isPresent()) {
            DecoderTyp decoderTyp = found.get();

            Decoder initial = new Decoder();
            initial.setDecoderTyp(decoderTyp);
            initial.setBezeichnung(decoderTyp.getBezeichnung());
            initial.setFahrstufe(decoderTyp.getFahrstufe());
            initial.setProtokoll(decoderTyp.getProtokoll());
            initial.setStatus(DecoderStatus.FREI);
            initial.setDeleted(false);
            
            final Decoder decoder = repository.saveAndFlush(initial);

            decoderTyp.getAdressen().forEach(a -> decoder.addAdress(
                            DecoderAdress.builder()
                                         .typ(a)
                                         .adress(a.getAdress())
                                         .build())
                            );
  
            decoderTyp.getCvs().forEach(c -> decoder.addCv(
                            DecoderCv.builder()
                                     .cv(c)
                                     .wert(c.getWerkseinstellung())
                                     .build())
                            );

            decoderTyp.getFunktionen().forEach(f -> decoder.addFunktion(
                            DecoderFunktion.builder()
                                           .funktion(f)
                                           .build())
                            );
            
        }

        return Optional.empty();
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
                        .map(a -> {
                            a.setAdress(adress);
                            return adressMutator.convert(adressRepository.saveAndFlush(a));
                        });
    }

    @Transactional
    public Optional<DecoderCvModel> updateCv(String decoderId, Integer cv, Integer wert) {
        return cvRepository.findByCv(decoderId, cv)
                        .map(c -> {
                            c.setWert(wert);
                            return cvMutator.convert(cvRepository.saveAndFlush(c));
                        });
    }

    @Transactional
    public Optional<DecoderFunktionModel> updateFunktion(String decoderId, Integer reihe, String funktion, String bezeichnung) {
        return funktionRepository.findByFunktion(decoderId, reihe, funktion)
                        .map(f -> {
                            f.setBezeichnung(bezeichnung);
                            return funktionMutator.convert(funktionRepository.saveAndFlush(f));
                        });
    }
}
