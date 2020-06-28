package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;

@Component("LichtModelMutator")
public class LichtModelMutator extends MutatorImpl<LichtModel, Licht> {

    public LichtModelMutator() {
        super(() -> new Licht(), new NamedTranscriber<LichtModel, Licht>());
    }

}
