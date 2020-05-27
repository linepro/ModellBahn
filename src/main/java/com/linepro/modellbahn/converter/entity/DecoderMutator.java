package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.util.exceptions.ResultCollector;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DecoderMutator implements Mutator<Decoder, DecoderModel> {

    @Autowired
    private final DecoderTypMutator decoderTypMutator;

    @Autowired
    private final ProtokollMutator protokollMutator;

    @Autowired
    private final DecoderAdressMutator adressMutator;

    @Autowired
    private final DecoderCvMutator cvMutator;

    @Autowired
    private final DecoderFunktionMutator funktionMutator;

    public DecoderModel applyFields(Decoder source, DecoderModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            applySummary(source, destination);
            destination.setStatus(source.getStatus());
            destination.setProtokoll(protokollMutator.summarize(source.getProtokoll()));
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
    public DecoderModel applySummary(Decoder source, DecoderModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setDecoderId(source.getDecoderId());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setDecoderTyp(decoderTypMutator.summarize(source.getDecoderTyp()));
        }
        
        return destination;
    }

    @Override
    public DecoderModel get() {
        return new DecoderModel();
    }
}
