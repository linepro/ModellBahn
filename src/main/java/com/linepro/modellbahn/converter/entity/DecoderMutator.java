package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.hibernate.collection.internal.PersistentSet;
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

    public DecoderModel apply(Decoder source, DecoderModel destination, int depth) {
        destination.setDecoderId(source.getDecoderId());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setDecoderTyp(decoderTypMutator.convert(source.getDecoderTyp(), depth));
        destination.setStatus(source.getStatus());
        destination.setProtokoll(protokollMutator.convert(source.getProtokoll()));
        destination.setFahrstufe(source.getFahrstufe());
        
        if (depth >= 0) {
            if (source.getAdressen() instanceof PersistentSet && ((PersistentSet) source.getAdressen()).wasInitialized()) {
                destination.setAdressen(source.getAdressen()
                                              .stream()
                                              .sorted()
                                              .map(a -> attempt(() ->adressMutator.convert(a)))
                                              .collect(new ResultCollector<>())
                                              .orElseThrow());
            }
            if (source.getCvs() instanceof PersistentSet && ((PersistentSet) source.getCvs()).wasInitialized()) {
                destination.setCvs(source.getCvs()
                                         .stream()
                                         .sorted()
                                         .map(c -> attempt(() ->cvMutator.convert(c)))
                                         .collect(new ResultCollector<>())
                                         .orElseThrow());
            }
            if (source.getFunktionen() instanceof PersistentSet && ((PersistentSet) source.getFunktionen()).wasInitialized()) {
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
