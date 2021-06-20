package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderFunktionModelTranscriber;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.repository.DecoderTypFunktionRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;

@Component(PREFIX + "DecoderFunktionModelMutator")
public class DecoderFunktionModelMutator extends MutatorImpl<DecoderFunktionModel,DecoderFunktion> {

    @Autowired
    public DecoderFunktionModelMutator(DecoderLookup decoderLookup, DecoderTypFunktionRepository funktionLookup) {
        super(() -> new DecoderFunktion(), new DecoderFunktionModelTranscriber(decoderLookup, funktionLookup));
    }
}
