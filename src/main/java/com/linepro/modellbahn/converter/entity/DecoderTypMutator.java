package com.linepro.modellbahn.converter.entity;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DecoderTypMutator implements Mutator<DecoderTyp,DecoderTypModel> {

    @Autowired
    private final HerstellerMutator herstellerMutator;

    @Autowired
    private final ProtokollMutator protokollMutator;

    @Autowired
    private final DecoderTypAdressMutator adressMutator;

    @Autowired
    private final DecoderTypCvMutator cvMutator;

    @Autowired
    private final DecoderTypFunktionMutator funktionMutator;

    public DecoderTypModel apply(DecoderTyp source, DecoderTypModel destination, int depth) {
        destination.setHersteller(herstellerMutator.convert(source.getHersteller()));
        destination.setBestellNr(source.getBestellNr());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setIMax(source.getIMax());
        destination.setProtokoll(protokollMutator.convert(source.getProtokoll()));
        destination.setFahrstufe(source.getFahrstufe());
        destination.setSound(source.getSound());
        destination.setKonfiguration(source.getKonfiguration());
        destination.setStecker(source.getStecker());
        destination.setAnleitungen(source.getAnleitungen());
        
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
    public DecoderTypModel get() {
        return new DecoderTypModel();
    }
}
