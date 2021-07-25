package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypAdressTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

@Component(PREFIX + "DecoderTypAdressMapper")
public class DecoderTypAdressMapper extends MapperImpl<DecoderTypAdress, DecoderTypAdressModel> {

    public DecoderTypAdressMapper() {
        super(() -> new DecoderTypAdressModel(), new DecoderTypAdressTranscriber());
    }
}
