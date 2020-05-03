package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;

@Component
public class MotorTypModelConverter extends NamedConverter<MotorTypModel,MotorTyp> {

    public MotorTypModelConverter() {
        super(() -> new MotorTypModel());
    }
}
