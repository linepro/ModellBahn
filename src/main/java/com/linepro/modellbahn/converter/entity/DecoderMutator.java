package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.util.exceptions.ResultCollector;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DecoderMutator implements Mutator<Decoder, DecoderModel> {

    @Autowired
    private final DecoderTypMutator decoderTypMutator;
    
    @Autowired
    private final DecoderAdressMutator adressMutator;

    @Autowired
    private final DecoderCvMutator cvMutator;

    @Autowired
    private final DecoderFunktionMutator funktionMutator;

    @Override
    public DecoderModel apply(Decoder source, DecoderModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            final DecoderTypModel decoderTyp = decoderTypMutator.convert(source.getDecoderTyp());

            destination.setDecoderId(source.getDecoderId());
            destination.setHersteller(decoderTyp.getHersteller());
            destination.setBestellNr(decoderTyp.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setIMax(decoderTyp.getIMax());
            destination.setSound(decoderTyp.getSound());
            destination.setKonfiguration(decoderTyp.getKonfiguration());
            destination.setStecker(decoderTyp.getStecker());
            destination.setAnleitungen(decoderTyp.getAnleitungen());
            destination.setStatus(source.getStatus());
            destination.setProtokoll(getCode(source.getProtokoll()));
            destination.setFahrstufe(source.getFahrstufe());

            if (isAvailable(source.getAdressen())) {
                destination.setAdressen(source.getAdressen()
                                              .stream()
                                              .sorted()
                                              .map(a -> attempt(() ->adressMutator.convert(a)))
                                              .collect(new ResultCollector<>())
                                              .orElseThrow());
            }

            if (isAvailable(source.getCvs())) {
                destination.setCvs(source.getCvs()
                                         .stream()
                                         .sorted()
                                         .map(c -> attempt(() ->cvMutator.convert(c)))
                                         .collect(new ResultCollector<>())
                                         .orElseThrow());
            }

            if (isAvailable(source.getFunktionen())) {
                destination.setFunktionen(source.getFunktionen()
                                                .stream()
                                                .sorted()
                                                .map(f -> attempt(() -> funktionMutator.convert(f)))
                                                .collect(new ResultCollector<>())
                                                .orElseThrow());
            }
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public DecoderModel get() {
        return new DecoderModel();
    }
}
