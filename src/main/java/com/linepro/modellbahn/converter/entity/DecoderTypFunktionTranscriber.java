package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

public class DecoderTypFunktionTranscriber implements Transcriber<DecoderTypFunktion,DecoderTypFunktionModel> {
    public DecoderTypFunktionModel apply(DecoderTypFunktion source, DecoderTypFunktionModel destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
