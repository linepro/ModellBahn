package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;

@Component(PREFIX + "MotorTypMutator")
public class MotorTypMutator extends MutatorImpl<MotorTyp, MotorTypModel> {

    public MotorTypMutator(PathMutator pathMutator) {
        super(() -> new MotorTypModel(), new NamedAbbildungTranscriber<>(pathMutator));
    }

}
