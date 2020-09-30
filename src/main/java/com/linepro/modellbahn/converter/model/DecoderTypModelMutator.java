package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderTypModelTranscriber;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.repository.HerstellerRepository;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

@Component(PREFIX + "DecoderTypModelMutator")
public class DecoderTypModelMutator extends MutatorImpl<DecoderTypModel, DecoderTyp> {

    @Autowired
    public DecoderTypModelMutator(HerstellerRepository herstellerRepository, ProtokollRepository protokollRepository, ItemLookup lookup) {
        super(()-> new DecoderTyp(), new DecoderTypModelTranscriber(herstellerRepository, protokollRepository, lookup));
    }
}
