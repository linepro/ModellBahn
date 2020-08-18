package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

public class AnderungModelTranscriber implements Transcriber<AnderungModel, Anderung> {

    @Override
    public Anderung apply(AnderungModel source, Anderung destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setAnderungId(source.getAnderungId());
            destination.setAnderungsDatum(source.getAnderungsDatum());
            destination.setAnderungsTyp(source.getAnderungsTyp());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setBezeichnung(source.getBezeichnung());
        }

        return destination;
    }
}
