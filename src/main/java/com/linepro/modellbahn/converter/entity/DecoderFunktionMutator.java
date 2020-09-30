package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderFunktionTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;

@Component(PREFIX + "DecoderFunktionMutator")
public class DecoderFunktionMutator extends MutatorImpl<DecoderFunktion, DecoderFunktionModel> {

    public DecoderFunktionMutator() {
        super(() -> new DecoderFunktionModel(), new DecoderFunktionTranscriber());
    }
}
