package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

public class DecoderTypCvModelTranscriber implements Transcriber<DecoderTypCvModel,DecoderTypCv> {
    public DecoderTypCv apply(DecoderTypCvModel source, DecoderTypCv destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
