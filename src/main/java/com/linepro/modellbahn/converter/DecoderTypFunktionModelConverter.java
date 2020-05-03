package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

@Component
public class DecoderTypFunktionModelConverter extends ItemModelConverter<DecoderTypFunktionModel,DecoderTypFunktion> {

    public DecoderTypFunktionModelConverter() {
        super(() -> new DecoderTypFunktionModel());
    }
}
