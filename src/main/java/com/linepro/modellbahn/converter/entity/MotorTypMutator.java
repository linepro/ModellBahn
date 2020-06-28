package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;

@Component("MotorTypMutator")
public class MotorTypMutator extends MutatorImpl<MotorTyp, MotorTypModel> {

    public MotorTypMutator() {
        super(() -> new MotorTypModel(), new NamedTranscriber<>());
    }

}
