package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;

@Component
public class DecoderFunktionModelConverter extends ItemModelConverter<DecoderFunktionModel,DecoderFunktion> {

    public DecoderFunktionModelConverter() {
        super(() -> new DecoderFunktionModel());
    }
}
