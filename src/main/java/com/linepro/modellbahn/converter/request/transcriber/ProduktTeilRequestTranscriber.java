package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.repository.lookup.ProduktLookup;
import com.linepro.modellbahn.request.ProduktTeilRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ProduktTeilRequestTranscriber")
public class ProduktTeilRequestTranscriber implements Transcriber<ProduktTeilRequest, ProduktTeil> {

    private final ProduktLookup produktLookup;

    @Override
    public ProduktTeil apply(ProduktTeilRequest source, ProduktTeil destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getProdukt() == null) {
                produktLookup.find(source.getHersteller(), source.getBestellNr()).ifPresent(p -> destination.setProdukt(p));
            }
            if (destination.getTeil() == null) {
                produktLookup.find(source.getTeilHersteller(), source.getTeilBestellNr()).ifPresent(t -> destination.setTeil(t));
            }
            destination.setMenge(source.getMenge());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
