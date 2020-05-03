package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.NamedConverter;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;

@Component
public class UnterKategorieModelConverter extends NamedConverter<UnterKategorieModel,UnterKategorie> {

    public UnterKategorieModelConverter() {
        super(() -> new UnterKategorieModel());
    }
}
