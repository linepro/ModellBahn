package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;

public class VorbildModelTranscriber implements Transcriber<VorbildModel, Vorbild> {
    public Vorbild apply(VorbildModel source, Vorbild destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
