package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Wahrung;
import com.linepro.modellbahn.model.WahrungModel;

@Component
public class WahrungModelConverter extends NamedConverter<WahrungModel,Wahrung> {

    public WahrungModelConverter() {
        super(() -> new WahrungModel());
    }
}
