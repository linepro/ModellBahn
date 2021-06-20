package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;

@Component(PREFIX + "DecoderTypMutator")
public class DecoderTypMutator extends MutatorImpl<DecoderTyp, DecoderTypModel> {

    @Autowired
    public DecoderTypMutator(DecoderTypAdressMutator adressMutator, DecoderTypCvMutator cvMutator, DecoderTypFunktionMutator funktionMutator,
                    PathMutator pathMutator) {
        super(() -> new DecoderTypModel(), new DecoderTypTranscriber(adressMutator, cvMutator, funktionMutator, pathMutator));
    }
}
