package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;

@Component
public class KategorieModelConverter extends NamedConverter<KategorieModel,Kategorie> {

    public KategorieModelConverter() {
        super(() -> new KategorieModel());
    }
}
