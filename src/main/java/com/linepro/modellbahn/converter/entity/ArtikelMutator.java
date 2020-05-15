package com.linepro.modellbahn.converter.entity;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;

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
            destination.setAnderungen(source.getAnderungen()
                                            .stream()
                                            .sorted()
                                            .map(a -> anderungMutator.convert(a))
                                            .collect(Collectors.toList()));
        }
        
        return destination;
    }

    @Override
    public ArtikelModel get() {
        return new ArtikelModel();
    }
}
