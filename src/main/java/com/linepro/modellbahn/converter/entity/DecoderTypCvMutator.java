package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypCvTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

@Component(PREFIX + "DecoderTypCvMutator")
public class DecoderTypCvMutator extends MutatorImpl<DecoderTypCv,DecoderTypCvModel> {

    public DecoderTypCvMutator() {
        super(() -> new DecoderTypCvModel(), new DecoderTypCvTranscriber());
    }
}
