package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.hibernate.collection.internal.PersistentSet;
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
        destination.setDeleted(source.getDeleted());
        
        if (depth >= 0) {
            if (source.getAdressen() instanceof PersistentSet && ((PersistentSet) source.getAdressen()).wasInitialized()) {
                destination.setAdressen(source.getAdressen()
                                .stream()
                                .sorted()
                                .map(a -> attempt(() -> adressMutator.convert(a)))
                                .collect(new ResultCollector<>())
                                .orElseThrow());
            }
            if (source.getCvs() instanceof PersistentSet && ((PersistentSet) source.getCvs()).wasInitialized()) {
                destination.setCvs(source.getCvs()
                                         .stream()
                                         .sorted()
                                         .map(c -> attempt(() -> cvMutator.convert(c)))
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
        }
        return destination;
    }

    @Override
    public DecoderTypModel get() {
        return new DecoderTypModel();
    }
}
