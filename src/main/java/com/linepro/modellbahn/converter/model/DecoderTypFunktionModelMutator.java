package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderTypFunktionModelTranscriber;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

@Component(PREFIX + "DecoderTypFunktionModelMutator")
public class DecoderTypFunktionModelMutator extends MutatorImpl<DecoderTypFunktionModel, DecoderTypFunktion> {

    public DecoderTypFunktionModelMutator() {
        super(() -> new DecoderTypFunktion(), new DecoderTypFunktionModelTranscriber());
    }
}
