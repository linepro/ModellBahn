package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;

@Component
public class LichtMutator extends MutatorImpl<Licht, LichtModel> {

    public LichtMutator() {
        super(() -> new LichtModel(), new NamedAbbildungTranscriber<Licht, LichtModel>());
    }

}
