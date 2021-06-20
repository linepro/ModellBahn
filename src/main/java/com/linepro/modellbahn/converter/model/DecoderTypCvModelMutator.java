package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderTypCvModelTranscriber;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;

@Component(PREFIX + "DecoderTypCvModelMutator")
public class DecoderTypCvModelMutator extends MutatorImpl<DecoderTypCvModel, DecoderTypCv> {

    @Autowired
    public DecoderTypCvModelMutator(DecoderTypLookup lookup) {
        super(() -> new DecoderTypCv(), new DecoderTypCvModelTranscriber(lookup));
    }
}
