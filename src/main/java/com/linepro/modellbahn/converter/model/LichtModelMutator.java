package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedModelTranscriber;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;

@Component(PREFIX + "LichtModelMutator")
public class LichtModelMutator extends MutatorImpl<LichtModel, Licht> {

    public LichtModelMutator() {
        super(() -> new Licht(), new NamedModelTranscriber<LichtModel, Licht>());
    }

}
