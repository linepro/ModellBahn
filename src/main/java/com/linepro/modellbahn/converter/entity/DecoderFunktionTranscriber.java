package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;

public class DecoderFunktionTranscriber implements Transcriber<DecoderFunktion,DecoderFunktionModel> {
    public DecoderFunktionModel apply(DecoderFunktion source, DecoderFunktionModel destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
