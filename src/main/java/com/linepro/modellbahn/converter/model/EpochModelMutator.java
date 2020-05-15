package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

@Component
public class EpochModelMutator extends MutatorImpl<EpochModel, Epoch> {

    public EpochModelMutator() {
        super(() -> new Epoch(), new NamedTranscriber<EpochModel, Epoch>());
    }

}
