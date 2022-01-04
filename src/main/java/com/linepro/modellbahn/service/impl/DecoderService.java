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

import com.linepro.modellbahn.converter.entity.DecoderCvMapper;
import com.linepro.modellbahn.converter.entity.DecoderFunktionMapper;
import com.linepro.modellbahn.converter.entity.DecoderMapper;
import com.linepro.modellbahn.converter.request.DecoderRequestMapper;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.persistence.util.AssetIdGenerator;
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

    private final DecoderCvRepository cvRepository;

    private final DecoderCvMapper cvMapper;

    private final DecoderFunktionRepository funktionRepository;

    private final DecoderFunktionMapper funktionMapper;

    private final AssetIdGenerator assetIdGenerator;

    @Autowired
    public DecoderService(DecoderRepository repository, DecoderTypRepository typRepository, DecoderRequestMapper decoderRequestMapper,
                    DecoderMapper decoderMapper, DecoderCvRepository cvRepository, DecoderCvMapper cvMapper, DecoderFunktionRepository funktionRepository,
                    DecoderFunktionMapper funktionMapper, AssetIdGenerator assetIdGenerator, DecoderLookup decoderLookup) {
        super(repository, decoderRequestMapper, decoderMapper, decoderLookup);
        this.repository = repository;

        this.typRepository = typRepository;

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

            Optional<Integer> adress = Optional.ofNullable(request.getAdress());

            Decoder initial = new Decoder();
            request.setDecoderId(null);
            initial.setDecoderId(assetIdGenerator.getNextAssetId());
            initial.setDecoderTyp(decoderTyp);
            initial = requestMapper.apply(request, initial);
            initial.setDeleted(false);

            final Decoder decoder = repository.saveAndFlush(initial);

            decoderTyp.getCvs()
                      .forEach(
                          c -> {
                              Integer wert = c.getWerkseinstellung();

                              if (DecoderCvRepository.ADRESSE.equals(c.getBezeichnung())) {
                                  wert = decoder.getAdress() != null ? decoder.getAdress() : c.getWerkseinstellung();
                                  decoder.setAdress(wert);
                              }

                              decoder.addCv(
                                          DecoderCv.builder()
                                                   .cv(c)
                                                   .wert(wert)
                                                   .deleted(false)
                                                   .build()
                                                   );
                              }
                          );

            decoderTyp.getFunktionen()
                      .forEach(
                          f -> decoder.addFunktion(
                                          DecoderFunktion.builder()
                                                         .funktion(f)
                                                         .bezeichnung(f.getBezeichnung())
                                                         .deleted(false)
                                                         .build()
                                                         )
                          );

            decoder.setAdress(adress.orElse(null));

            return Optional.of(entityMapper.convert(repository.saveAndFlush(decoder)));
        }

        return Optional.empty();
    }

    public Optional<DecoderModel> get(String decoderId) {
        return super.get(DecoderModel.builder().decoderId(decoderId).build());
    }

    @Transactional
    public Optional<DecoderModel> update(String decoderId, DecoderRequest request) {
        return lookup.find(DecoderModel.builder().decoderId(decoderId).build())
                     .map(d -> {
                         Boolean deleted = d.getDeleted();
                         Integer adress = d.getAdress();
                         d = requestMapper.apply(request, d);
                         d.setDeleted(deleted);

                         if (adress != d.getAdress()) {
                             cvRepository.findByBezeichnung(decoderId, DecoderCvRepository.ADRESSE)
                                         .map(c -> {
                                             c.setWert(request.getAdress());
                                             return cvRepository.save(c);
                                             });
                         }

                         return repository.saveAndFlush(d);
                     })
                     .flatMap(d -> lookup.find(d)) // Fetch again to populate entity graphs
                     .map(d -> entityMapper.convert(d));
    }

    public boolean delete(String decoderId) {
        return super.delete(DecoderModel.builder().decoderId(decoderId).build());
    }

    @Transactional
    public Optional<DecoderCvModel> updateCv(String decoderId, Integer cv, Integer wert) {
        return cvRepository.findByCv(decoderId, cv)
                           .map(c -> {
                               if (DecoderCvRepository.ADRESSE.equals(c.getCv().getBezeichnung())) {
                                   repository.findByDecoderId(decoderId)
                                             .map(d -> {
                                                  d.setAdress(wert);
                                                  return repository.save(d);
                                              });
                               }

                               c.setWert(wert);

                               return cvMapper.convert(cvRepository.saveAndFlush(c));
                               });
    }

    @Transactional
    public Optional<DecoderFunktionModel> updateFunktion(String decoderId, String funktion, String bezeichnung) {
        return funktionRepository.findByFunktion(decoderId, funktion)
                                 .map(f -> {
                                     f.setBezeichnung(bezeichnung); return funktionMapper.convert(funktionRepository.saveAndFlush(f));
                                     });
    }
}
