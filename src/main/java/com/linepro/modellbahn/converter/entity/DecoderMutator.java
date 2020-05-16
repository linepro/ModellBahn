package com.linepro.modellbahn.converter.entity;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;

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
        
        if (depth > 0) {
            destination.setAdressen(source.getAdressen()
                                          .stream()
                                          .sorted()
                                          .map(a -> adressMutator.convert(a))
                                          .collect(Collectors.toList()));
            destination.setCvs(source.getCvs()
                                     .stream()
                                     .sorted()
                                     .map(c -> cvMutator.convert(c))
                                     .collect(Collectors.toList()));
            destination.setFunktionen(source.getFunktionen()
                                            .stream()
                                            .sorted()
                                            .map(f -> funktionMutator.convert(f))
                                            .collect(Collectors.toList()));
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public DecoderModel get() {
        return new DecoderModel();
    }
}
