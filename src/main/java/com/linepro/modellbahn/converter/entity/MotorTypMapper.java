package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;

@Component(PREFIX + "MotorTypMapper")
public class MotorTypMapper extends MapperImpl<MotorTyp, MotorTypModel> {

    public MotorTypMapper(PathMapper pathMapper) {
        super(() -> new MotorTypModel(), new NamedAbbildungTranscriber<>(pathMapper));
    }

}
