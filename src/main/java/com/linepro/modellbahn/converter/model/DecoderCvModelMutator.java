package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderCvModelTranscriber;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.repository.DecoderTypCvRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;

@Component(PREFIX + "DecoderCvModelMutator")
public class DecoderCvModelMutator extends MutatorImpl<DecoderCvModel,DecoderCv> {

    @Autowired
    public DecoderCvModelMutator(DecoderLookup decoderLookup, DecoderTypCvRepository cvLookup) {
        super(() -> new DecoderCv(), new DecoderCvModelTranscriber(decoderLookup, cvLookup));
    }
}
