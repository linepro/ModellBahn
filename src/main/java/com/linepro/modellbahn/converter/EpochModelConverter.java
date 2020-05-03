package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

@Component
public class EpochModelConverter extends NamedConverter<EpochModel,Epoch> {

    public EpochModelConverter() {
        super(() -> new EpochModel());
    }
}
