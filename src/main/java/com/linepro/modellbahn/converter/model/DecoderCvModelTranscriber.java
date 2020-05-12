package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;

public class DecoderCvModelTranscriber implements Transcriber<DecoderCvModel,DecoderCv> {
    public DecoderCv apply(DecoderCvModel source, DecoderCv destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
