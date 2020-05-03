package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedWithAbbildungConverter;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.KupplungModel;

@Component
public class KupplungModelConverter extends NamedWithAbbildungConverter<KupplungModel,Kupplung> {

    public KupplungModelConverter() {
        super(() -> new KupplungModel());
    }
}
