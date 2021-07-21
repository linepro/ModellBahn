package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderAdressTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;

@Component(PREFIX + "DecoderAdressMutator")
public class DecoderAdressMutator extends MapperImpl<DecoderAdress, DecoderAdressModel> {

    public DecoderAdressMutator() {
        super(() -> new DecoderAdressModel(), new DecoderAdressTranscriber());
    }
}
