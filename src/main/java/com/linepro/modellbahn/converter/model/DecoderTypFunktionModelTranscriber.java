package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

public class DecoderTypFunktionModelTranscriber implements Transcriber<DecoderTypFunktionModel, DecoderTypFunktion> {
    public DecoderTypFunktion apply(DecoderTypFunktionModel source, DecoderTypFunktion destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
