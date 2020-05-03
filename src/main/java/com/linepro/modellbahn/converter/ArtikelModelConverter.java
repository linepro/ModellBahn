package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;

@Component
public class ArtikelModelConverter extends ItemModelConverter<ArtikelModel,Artikel> {

    public ArtikelModelConverter() {
        super(() -> new ArtikelModel());
    }
}
