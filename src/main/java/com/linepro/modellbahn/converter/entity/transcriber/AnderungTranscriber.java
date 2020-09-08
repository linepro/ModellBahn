package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

public class AnderungTranscriber implements Transcriber<Anderung, AnderungModel> {
    @Override
    public AnderungModel apply(Anderung source, AnderungModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setArtikelId(source.getArtikel().getArtikelId());
            destination.setAnderungId(source.getAnderungId());
            destination.setAnderungsDatum(source.getAnderungsDatum());
            destination.setAnderungsTyp(source.getAnderungsTyp());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setStuck(source.getStuck());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }
}
