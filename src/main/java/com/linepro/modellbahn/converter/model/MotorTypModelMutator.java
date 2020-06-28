package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;

@Component("MotorTypModelMutator")
public class MotorTypModelMutator extends MutatorImpl<MotorTypModel, MotorTyp> {

    public MotorTypModelMutator() {
        super(() -> new MotorTyp(), new NamedTranscriber<MotorTypModel, MotorTyp>());
    }
}
