package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;

@Component
public class LichtModelMutator extends MutatorImpl<LichtModel, Licht> {

    public LichtModelMutator() {
        super(() -> new Licht(), new NamedAbbildungTranscriber<LichtModel, Licht>());
    }

}
