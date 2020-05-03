package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;

@Component
public class AnderungModelConverter extends ItemModelConverter<AnderungModel,Anderung> {

    public AnderungModelConverter() {
        super(() -> new AnderungModel());
    }
}
