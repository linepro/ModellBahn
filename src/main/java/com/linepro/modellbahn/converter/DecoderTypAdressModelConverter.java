package com.linepro.modellbahn.converter;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.base.ItemModelConverter;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

@Component
public class DecoderTypAdressModelConverter extends ItemModelConverter<DecoderTypAdressModel,DecoderTypAdress> {

    public DecoderTypAdressModelConverter() {
        super(() -> new DecoderTypAdressModel());
    }
}
