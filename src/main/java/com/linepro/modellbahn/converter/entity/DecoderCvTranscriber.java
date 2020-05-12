package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;

public class DecoderCvTranscriber implements Transcriber<DecoderCv,DecoderCvModel> {
    public DecoderCvModel apply(DecoderCv source, DecoderCvModel destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
