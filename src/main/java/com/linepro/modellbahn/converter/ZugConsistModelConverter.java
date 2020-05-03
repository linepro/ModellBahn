package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;

@Component
public class ZugConsistModelConverter extends ItemModelConverter<ZugConsistModel,ZugConsist> {

    public ZugConsistModelConverter() {
        super(() -> new ZugConsistModel());
    }
}
