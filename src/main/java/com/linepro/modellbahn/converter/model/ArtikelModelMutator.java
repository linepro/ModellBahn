package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.repository.KupplungRepository;
import com.linepro.modellbahn.repository.LichtRepository;
import com.linepro.modellbahn.repository.MotorTypRepository;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.repository.lookup.ProduktLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ArtikelModelMutator implements Mutator<ArtikelModel,Artikel> {

    @Autowired
    private final ProduktLookup produktLookup;

    @Autowired
    private final SteuerungRepository steuerungRepository;

    @Autowired
    private final MotorTypRepository motorTypRepository;

    @Autowired
    private final LichtRepository lichtRepository;

    @Autowired
    private final KupplungRepository kupplungRepository;

    @Autowired
    private final DecoderLookup decoderLookup;

    @Autowired
    private final ItemLookup lookup;

    @Override
    public Artikel convert(ArtikelModel source) {
        if (source != null) {
            final Artikel destination = get();

            destination.setArtikelId(source.getArtikelId());

            return apply(source, destination);
        }

        return null;
    }

    @Override
    public Artikel apply(ArtikelModel source, Artikel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setProdukt(produktLookup.find(source.getHersteller(), source.getBestellNr()));
            destination.setKaufdatum(source.getKaufdatum());
            destination.setWahrung(source.getWahrung());
            destination.setSteuerung(lookup.find(source.getSteuerung(), steuerungRepository));
            destination.setMotorTyp(lookup.find(source.getMotorTyp(), motorTypRepository));
            destination.setLicht(lookup.find(source.getLicht(), lichtRepository));
            destination.setKupplung(lookup.find(source.getKupplung(), kupplungRepository));
            destination.setDecoder(decoderLookup.find(source.getDecoder()));
            destination.setBezeichnung(source.getBezeichnung());
            destination.setPreis(source.getPreis());
            destination.setStuck(source.getStuck());
            destination.setVerbleibende(source.getVerbleibende());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setBeladung(source.getBeladung());
            destination.setStatus(source.getStatus());
        }
        
        return destination;
    }
    
    @Override
    public Artikel get() {
        return new Artikel();
    }
}
