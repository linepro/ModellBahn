package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;

@Component
public class VorbildModelConverter extends ItemModelConverter<VorbildModel,Vorbild> {

    public VorbildModelConverter() {
        super(() -> new VorbildModel());
    }
}
