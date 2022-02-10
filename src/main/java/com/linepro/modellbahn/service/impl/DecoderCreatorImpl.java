package com.linepro.modellbahn.service.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.request.transcriber.DecoderRequestTranscriber;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.persistence.util.AssetIdGenerator;
import com.linepro.modellbahn.repository.DecoderCvRepository;
import com.linepro.modellbahn.repository.DecoderRepository;
import com.linepro.modellbahn.repository.DecoderTypRepository;
import com.linepro.modellbahn.request.DecoderRequest;
import com.linepro.modellbahn.service.DecoderCreator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(PREFIX + "DecoderCreator")
@RequiredArgsConstructor
public class DecoderCreatorImpl implements DecoderCreator {

    private final DecoderRepository repository;

    private final DecoderTypRepository typRepository;

    private final AssetIdGenerator assetIdGenerator;

    private final DecoderRequestTranscriber requestMapper;

    @Override
    public Optional<Decoder> create(DecoderRequest request) {
        Optional<DecoderTyp> found = typRepository.findByBestellNr(request.getHersteller(), request.getBestellNr());

        if (found.isPresent()) {
            DecoderTyp decoderTyp = found.get();

            Optional<Integer> adress = Optional.ofNullable(request.getAdress());

            Decoder initial = new Decoder();
            initial.setDecoderId(
                        Optional.ofNullable(request.getDecoderId())
                                .orElse(assetIdGenerator.getNextAssetId())
                        );
            initial.setDecoderTyp(decoderTyp);
            initial = requestMapper.apply(request, initial);
            initial.setDeleted(false);

            final Decoder decoder = repository.saveAndFlush(initial);

            decoderTyp.getCvs()
                      .forEach(c -> {
                Integer wert = c.getWerkseinstellung();

                if (DecoderCvRepository.ADRESSE.equals(c.getBezeichnung())) {
                    decoder.setAdress(
                                Optional.ofNullable(decoder.getAdress())
                                        .orElse(c.getWerkseinstellung())
                                        );
                }

                decoder.addCv(
                            DecoderCv.builder()
                                     .cv(c)
                                     .wert(wert)
                                     .deleted(false)
                                     .build()
                        );
            });

            decoderTyp.getFunktionen()
                      .forEach(f -> decoder.addFunktion(
                           DecoderFunktion.builder()
                                          .funktion(f)
                                          .bezeichnung(f.getBezeichnung())
                                          .deleted(false)
                                          .build())
                       );

            decoder.setAdress(adress.orElse(null));

            log.info("Decoder {} created", decoder);

            return Optional.of(repository.saveAndFlush(decoder));
        } else {
            log.debug("Decoder {} already exists", request);
        }

        return Optional.empty();
    }
}
