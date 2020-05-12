package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

@Component
public class AnderungTranscriber implements Transcriber<Anderung,AnderungModel> {

    public AnderungModel apply(Anderung source, AnderungModel destination) {
        destination.setArtikelId(source.getArtikel().getArtikelId());
        destination.setAnderungId(source.getAnderungId());
        destination.setAnderungsDatum(source.getAnderungsDatum());
        destination.setAnderungsTyp(source.getAnderungsTyp());
        destination.setAnmerkung(source.getAnmerkung());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setStuck(source.getStuck());
        return destination;
    }
}
