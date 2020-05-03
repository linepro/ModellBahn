package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.model.GattungModel;

@Component
public class GattungModelConverter extends NamedConverter<GattungModel,Gattung> {

    public GattungModelConverter() {
        super(() -> new GattungModel());
    }
}
