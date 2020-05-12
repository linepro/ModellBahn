package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;

public class DecoderTypModelTranscriber implements Transcriber<DecoderTypModel,DecoderTyp> {
    public DecoderTyp apply(DecoderTypModel source, DecoderTyp destination) {
        //destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
