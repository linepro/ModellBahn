package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderModelTranscriber;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

@Component(PREFIX + "DecoderModelMutator")
public class DecoderModelMutator extends MutatorImpl<DecoderModel, Decoder> {

    @Autowired
    public DecoderModelMutator(ProtokollRepository protokollRepository, ItemLookup lookup) {
        super(() -> new Decoder(), new DecoderModelTranscriber(protokollRepository, lookup));
    }
}
