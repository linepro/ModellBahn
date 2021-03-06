package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
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
public class ArtikelModelTranscriber implements Transcriber<ArtikelModel, Artikel> {

    private final ProduktLookup produktLookup;

    private final SteuerungRepository steuerungRepository;

    private final MotorTypRepository motorTypRepository;

    private final LichtRepository lichtRepository;

    private final KupplungRepository kupplungRepository;

    private final DecoderLookup decoderLookup;

    private final ItemLookup lookup;

    @Override
    public Artikel apply(ArtikelModel source, Artikel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getArtikelId() == null) {
                destination.setArtikelId(source.getArtikelId());
            }
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
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
