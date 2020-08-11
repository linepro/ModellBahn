package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedModelTranscriber;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;

@Component(PREFIX + "MotorTypModelMutator")
public class MotorTypModelMutator extends MutatorImpl<MotorTypModel, MotorTyp> {

    public MotorTypModelMutator() {
        super(() -> new MotorTyp(), new NamedModelTranscriber<MotorTypModel, MotorTyp>());
    }
}
