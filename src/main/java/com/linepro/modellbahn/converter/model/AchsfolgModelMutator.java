package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.NamedModelTranscriber;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;

@Component(PREFIX + "AchsfolgModelMutator")
public class AchsfolgModelMutator extends MutatorImpl<AchsfolgModel, Achsfolg> {

    public AchsfolgModelMutator() {
        super(() -> new Achsfolg(), new NamedModelTranscriber<>());
    }
}
