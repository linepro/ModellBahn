package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;

@Component
public class DecoderTypModelConverter extends ItemModelConverter<DecoderTypModel,DecoderTyp> {

    public DecoderTypModelConverter() {
        super(() -> new DecoderTypModel());
    }
}
