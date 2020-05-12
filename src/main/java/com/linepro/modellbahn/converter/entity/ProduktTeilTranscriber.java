package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

public class ProduktTeilTranscriber implements Transcriber<ProduktTeil,ProduktTeilModel> {
    public ProduktTeilModel apply(ProduktTeil source, ProduktTeilModel destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
