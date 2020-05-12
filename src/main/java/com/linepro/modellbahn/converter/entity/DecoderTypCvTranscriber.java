package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

public class DecoderTypCvTranscriber implements Transcriber<DecoderTypCv,DecoderTypCvModel> {
    public DecoderTypCvModel apply(DecoderTypCv source, DecoderTypCvModel destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
