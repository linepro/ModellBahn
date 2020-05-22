package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.util.exceptions.ResultCollector;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ArtikelMutator implements Mutator<Artikel, ArtikelModel> {

    @Autowired
    private final MotorTypMutator motorTypMutator;

    @Autowired
    private final SteuerungMutator steuerungMutator;

    @Autowired
    private final LichtMutator lichtMutator;

    @Autowired
    private final KupplungMutator kupplungMutator;

    @Autowired
    private final DecoderMutator decoderMutator;

    @Autowired
    private final AnderungMutator anderungMutator;

    @Override
    public ArtikelModel apply(Artikel source, ArtikelModel destination, int depth) {
        destination.setArtikelId(source.getArtikelId());
        destination.setKaufdatum(source.getKaufdatum());
        destination.setWahrung(source.getWahrung());
        destination.setSteuerung(steuerungMutator.convert(source.getSteuerung()));
        destination.setMotorTyp(motorTypMutator.convert(source.getMotorTyp()));
        destination.setLicht(lichtMutator.convert(source.getLicht()));
        destination.setKupplung(kupplungMutator.convert(source.getKupplung()));
        destination.setDecoder(decoderMutator.convert(source.getDecoder()));
        destination.setBezeichnung(source.getBezeichnung());
        destination.setPreis(source.getPreis());
        destination.setStuck(source.getStuck());
        destination.setVerbleibende(source.getVerbleibende());
        destination.setAnmerkung(source.getAnmerkung());
        destination.setBeladung(source.getBeladung());
        destination.setAbbildung(source.getAbbildung());
        destination.setStatus(source.getStatus());
        destination.setDeleted(source.getDeleted());

        if (depth >= 0) {
            if (source.getAnderungen() instanceof PersistentSet && ((PersistentSet) source.getAnderungen()).wasInitialized()) {
               destination.setAnderungen(source.getAnderungen()
                                                .stream()
                                                .sorted()
                                                .map(a -> attempt(() -> anderungMutator.convert(a)))
                                                .collect(new ResultCollector<>())
                                                .orElseThrow());
            }
        }
        
        return destination;
    }

    @Override
    public ArtikelModel get() {
        return new ArtikelModel();
    }
}
