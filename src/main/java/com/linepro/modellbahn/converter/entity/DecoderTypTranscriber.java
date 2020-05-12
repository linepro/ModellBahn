package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;

public class DecoderTypTranscriber implements Transcriber<DecoderTyp,DecoderTypModel> {
    public DecoderTypModel apply(DecoderTyp source, DecoderTypModel destination) {
        //destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
