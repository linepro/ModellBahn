package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderTypCvModelTranscriber;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

@Component(PREFIX + "DecoderTypCvModelMutator")
public class DecoderTypCvModelMutator extends MutatorImpl<DecoderTypCvModel, DecoderTypCv> {

    public DecoderTypCvModelMutator() {
        super(() -> new DecoderTypCv(), new DecoderTypCvModelTranscriber());
    }
}
