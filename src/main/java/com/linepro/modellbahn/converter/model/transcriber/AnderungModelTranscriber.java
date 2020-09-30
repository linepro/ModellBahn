package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.repository.lookup.ArtikelLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnderungModelTranscriber implements Transcriber<AnderungModel, Anderung> {

    private final ArtikelLookup artikelLookup;

    @Override
    public Anderung apply(AnderungModel source, Anderung destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getArtikel() == null) {
                destination.setArtikel(artikelLookup.find(source.getArtikelId()));
            }
            if (destination.getAnderungId() == null) {
                destination.setAnderungId(source.getAnderungId());
            }
            destination.setAnderungsDatum(source.getAnderungsDatum());
            destination.setAnderungsTyp(source.getAnderungsTyp());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setStuck(source.getStuck());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
