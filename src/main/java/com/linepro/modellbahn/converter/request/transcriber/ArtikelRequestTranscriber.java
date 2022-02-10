package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.repository.lookup.KupplungLookup;
import com.linepro.modellbahn.repository.lookup.LichtLookup;
import com.linepro.modellbahn.repository.lookup.MotorTypLookup;
import com.linepro.modellbahn.repository.lookup.ProduktLookup;
import com.linepro.modellbahn.repository.lookup.SteuerungLookup;
import com.linepro.modellbahn.request.ArtikelRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ArtikelRequestTranscriber")
public class ArtikelRequestTranscriber implements Transcriber<ArtikelRequest, Artikel> {

    private final ProduktLookup produktLookup;

    private final SteuerungLookup steuerungLookup;

    private final MotorTypLookup motorTypLookup;

    private final LichtLookup lichtLookup;

    private final KupplungLookup kupplungLookup;

    @Override
    public Artikel apply(ArtikelRequest source, Artikel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getArtikelId() == null) {
                destination.setArtikelId(source.getArtikelId());
            }
            produktLookup.find(source.getHersteller(), source.getBestellNr())
                         .ifPresent(p -> destination.setProdukt(p));
            destination.setKaufdatum(source.getKaufdatum());
            destination.setWahrung(source.getWahrung());
            destination.setSteuerung(steuerungLookup.find(source.getSteuerung()).orElse(null));
            destination.setMotorTyp(motorTypLookup.find(source.getMotorTyp()).orElse(null));
            destination.setLicht(lichtLookup.find(source.getLicht()).orElse(null));
            destination.setKupplung(kupplungLookup.find(source.getKupplung()).orElse(null));
            destination.setBezeichnung(source.getBezeichnung());
            destination.setPreis(source.getPreis());
            destination.setMenge(source.getMenge());
            destination.setVerbleibende(source.getVerbleibende());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setBeladung(source.getBeladung());
            destination.setStatus(source.getStatus());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
