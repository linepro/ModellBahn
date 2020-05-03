package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;

@Component
public class DecoderModelConverter extends ItemModelConverter<DecoderModel,Decoder> {

    public DecoderModelConverter() {
        super(() -> new DecoderModel());
    }
}
