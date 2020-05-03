package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;

@Component
public class SpurweiteModelConverter extends NamedConverter<SpurweiteModel,Spurweite> {

    public SpurweiteModelConverter() {
        super(() -> new SpurweiteModel());
    }
}
