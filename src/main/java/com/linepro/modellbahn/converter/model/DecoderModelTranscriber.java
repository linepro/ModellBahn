package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;

public class DecoderModelTranscriber implements Transcriber<DecoderModel, Decoder> {
    public Decoder apply(DecoderModel source, Decoder destination) {
        //destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
