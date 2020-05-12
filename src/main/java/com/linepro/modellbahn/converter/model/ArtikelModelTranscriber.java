package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;

public class ArtikelModelTranscriber implements Transcriber<ArtikelModel,Artikel> {
    public Artikel apply(ArtikelModel source, Artikel destination) {
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
