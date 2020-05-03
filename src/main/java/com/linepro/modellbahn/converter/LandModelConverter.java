package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Land;
import com.linepro.modellbahn.model.LandModel;

@Component
public class LandModelConverter extends NamedConverter<LandModel,Land> {

    public LandModelConverter() {
        super(() -> new LandModel());
    }
}
