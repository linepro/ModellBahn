package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;

@Component
public class AchsfolgModelMutator extends MutatorImpl<AchsfolgModel, Achsfolg> {

    public AchsfolgModelMutator() {
        super(() -> new Achsfolg(), new NamedTranscriber<>());
    }
}
