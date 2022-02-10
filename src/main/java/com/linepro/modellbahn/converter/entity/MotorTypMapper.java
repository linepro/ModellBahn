package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;

@Component(PREFIX + "MotorTypMapper")
public class MotorTypMapper extends MapperImpl<MotorTyp, MotorTypModel> {

    @SuppressWarnings("unchecked")
    public MotorTypMapper(@Qualifier(PREFIX + "NamedAbbildungTranscriber") Transcriber<?, ?> transcriber) {
        super(() -> new MotorTypModel(), (Transcriber<MotorTyp, MotorTypModel>) transcriber);
    }

}
