package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypFunktionTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

@Component(PREFIX + "DecoderTypFunktionMutator")
public class DecoderTypFunktionMutator extends MutatorImpl<DecoderTypFunktion,DecoderTypFunktionModel> {

    public DecoderTypFunktionMutator() {
        super(() -> new DecoderTypFunktionModel(), new DecoderTypFunktionTranscriber());
    }
}
