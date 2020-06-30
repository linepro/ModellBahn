package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;

@Component(PREFIX + "AchsfolgMutator")
public class AchsfolgMutator extends MutatorImpl<Achsfolg, AchsfolgModel> {

    public AchsfolgMutator() {
        super(() -> new AchsfolgModel(), new NamedTranscriber<Achsfolg, AchsfolgModel>());
    }

}
