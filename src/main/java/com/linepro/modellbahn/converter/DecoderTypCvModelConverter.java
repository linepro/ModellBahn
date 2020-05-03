package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;

@Component
public class DecoderTypCvModelConverter extends ItemModelConverter<DecoderTypCvModel,DecoderTypCv> {

    public DecoderTypCvModelConverter() {
        super(() -> new DecoderTypCvModel());
    }
}
