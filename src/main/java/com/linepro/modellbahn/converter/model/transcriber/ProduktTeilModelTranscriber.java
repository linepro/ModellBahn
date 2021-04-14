package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.repository.lookup.ProduktLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProduktTeilModelTranscriber implements Transcriber<ProduktTeilModel, ProduktTeil> {

    private final ProduktLookup produktLookup;

    @Override
    public ProduktTeil apply(ProduktTeilModel source, ProduktTeil destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getProdukt() == null) {
                destination.setProdukt(produktLookup.find(source.getHersteller(), source.getBestellNr()));
            }
            if (destination.getTeil() == null) {
                destination.setTeil(produktLookup.find(source.getTeilHersteller(), source.getTeilBestellNr()));
            }
            destination.setMenge(source.getMenge());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
