package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

public class ProduktTeilModelTranscriber implements Transcriber<ProduktTeilModel, ProduktTeil> {
    public ProduktTeil apply(ProduktTeilModel source, ProduktTeil destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
