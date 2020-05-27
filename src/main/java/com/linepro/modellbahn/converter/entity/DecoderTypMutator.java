package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.util.exceptions.ResultCollector;

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

    @Override
    public DecoderTypModel applyFields(DecoderTyp source, DecoderTypModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            applySummary(source, destination);
            destination.setIMax(source.getIMax());
            destination.setProtokoll(protokollMutator.summarize(source.getProtokoll()));
            destination.setFahrstufe(source.getFahrstufe());
            destination.setSound(source.getSound());
            destination.setKonfiguration(source.getKonfiguration());
            destination.setStecker(source.getStecker());
            destination.setAnleitungen(source.getAnleitungen());
            destination.setDeleted(source.getDeleted());
            
            if (isAvailable(source.getAdressen())) {
                destination.setAdressen(source.getAdressen()
                                .stream()
                                .sorted()
                                .map(a -> attempt(() -> adressMutator.convert(a)))
                                .collect(new ResultCollector<>())
                                .orElseThrow());
            }
            if (isAvailable(source.getCvs())) {
                destination.setCvs(source.getCvs()
                                         .stream()
                                         .sorted()
                                         .map(c -> attempt(() -> cvMutator.convert(c)))
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
        }
        
        return destination;
    }

    @Override
    public DecoderTypModel applySummary(DecoderTyp source, DecoderTypModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(herstellerMutator.convert(source.getHersteller()));
            destination.setBestellNr(source.getBestellNr());
            destination.setBezeichnung(source.getBezeichnung());
        }
        
        return destination;
    }

    @Override
    public DecoderTypModel get() {
        return new DecoderTypModel();
    }
}
