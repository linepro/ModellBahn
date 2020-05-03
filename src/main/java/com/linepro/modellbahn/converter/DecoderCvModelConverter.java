package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;

@Component
public class DecoderCvModelConverter extends ItemModelConverter<DecoderCvModel,DecoderCv> {

    public DecoderCvModelConverter() {
        super(() -> new DecoderCvModel());
    }
}
