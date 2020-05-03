package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;

@Component
public class LichtModelConverter extends NamedConverter<LichtModel,Licht> {

    public LichtModelConverter() {
        super(() -> new LichtModel());
    }
}
