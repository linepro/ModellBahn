package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;

@Component
public class SteuerungModelConverter extends NamedConverter<SteuerungModel,Steuerung> {

    public SteuerungModelConverter() {
        super(() -> new SteuerungModel());
    }
}
