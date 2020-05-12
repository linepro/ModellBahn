package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;

public class DecoderTranscriber implements Transcriber<Decoder, DecoderModel> {
    public DecoderModel apply(Decoder source, DecoderModel destination) {
        //destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
