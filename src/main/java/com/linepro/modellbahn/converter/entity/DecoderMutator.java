package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;

@Component(PREFIX + "DecoderMutator")
public class DecoderMutator extends MutatorImpl<Decoder, DecoderModel> {

    @Autowired
    public DecoderMutator(DecoderTypMutator decoderTypMutator, DecoderAdressMutator adressMutator, DecoderCvMutator cvMutator, DecoderFunktionMutator funktionMutator) {
        super(() -> new DecoderModel(), new DecoderTranscriber(decoderTypMutator, adressMutator, cvMutator, funktionMutator));
    }
}
