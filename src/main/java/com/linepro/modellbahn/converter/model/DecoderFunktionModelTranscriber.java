package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;

public class DecoderFunktionModelTranscriber implements Transcriber<DecoderFunktionModel,DecoderFunktion> {
    public DecoderFunktion apply(DecoderFunktionModel source, DecoderFunktion destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
