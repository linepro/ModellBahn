package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;

@Component
public class ProduktModelConverter extends ItemModelConverter<ProduktModel,Produkt> {

    public ProduktModelConverter() {
        super(() -> new ProduktModel());
    }
}
