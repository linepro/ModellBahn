package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

@Component
public class HerstellerModelConverter extends NamedConverter<HerstellerModel,Hersteller> {

    public HerstellerModelConverter() {
        super(() -> new HerstellerModel());
    }
}
