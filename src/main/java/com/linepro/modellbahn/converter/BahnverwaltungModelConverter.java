package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Component
public class BahnverwaltungModelConverter extends NamedConverter<BahnverwaltungModel,Bahnverwaltung> {

    public BahnverwaltungModelConverter() {
        super(() -> new BahnverwaltungModel());
    }
}
