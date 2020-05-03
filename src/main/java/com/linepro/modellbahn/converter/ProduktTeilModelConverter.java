package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.ProduktTeil;
import com.linepro.modellbahn.model.ProduktTeilModel;

@Component
public class ProduktTeilModelConverter extends ItemModelConverter<ProduktTeilModel,ProduktTeil> {

    public ProduktTeilModelConverter() {
        super(() -> new ProduktTeilModel());
    }
}
