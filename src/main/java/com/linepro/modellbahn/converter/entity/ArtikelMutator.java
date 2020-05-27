package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.util.exceptions.ResultCollector;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ArtikelMutator implements Mutator<Artikel, ArtikelModel> {

    @Autowired
    private final ProduktMutator produktMutator;
    
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
    public ArtikelModel applyFields(Artikel source, ArtikelModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            applySummary(source, destination);
            destination.setKaufdatum(source.getKaufdatum());
            destination.setWahrung(source.getWahrung());
            destination.setSteuerung(steuerungMutator.summarize(source.getSteuerung()));
            destination.setMotorTyp(motorTypMutator.summarize(source.getMotorTyp()));
            destination.setLicht(lichtMutator.summarize(source.getLicht()));
            destination.setKupplung(kupplungMutator.summarize(source.getKupplung()));
            destination.setDecoder(decoderMutator.summarize(source.getDecoder()));
            destination.setBezeichnung(source.getBezeichnung());
            destination.setPreis(source.getPreis());
            destination.setStuck(source.getStuck());
            destination.setVerbleibende(source.getVerbleibende());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setBeladung(source.getBeladung());
            destination.setAbbildung(source.getAbbildung());
            destination.setStatus(source.getStatus());
            destination.setDeleted(source.getDeleted());
    
            if (isAvailable(source.getAnderungen())) {
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
    public ArtikelModel applySummary(Artikel source, ArtikelModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setArtikelId(source.getArtikelId());
            destination.setBezeichnung(source.getBezeichnung());
            
            ProduktModel produkt = produktMutator.summarize(source.getProdukt());
            destination.setHersteller(produkt.getHersteller());
            destination.setBestellNr(produkt.getBezeichnung());
        }
        
        return destination;
    }

    @Override
    public ArtikelModel get() {
        return new ArtikelModel();
    }
}
