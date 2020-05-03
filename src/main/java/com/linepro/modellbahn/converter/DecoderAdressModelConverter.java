package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;

@Component
public class DecoderAdressModelConverter extends ItemModelConverter<DecoderAdressModel,DecoderAdress> {

    public DecoderAdressModelConverter() {
        super(() -> new DecoderAdressModel());
    }
}
