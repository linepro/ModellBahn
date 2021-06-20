package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderTypAdressTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

@Component(PREFIX + "DecoderTypAdressMutator")
public class DecoderTypAdressMutator extends MutatorImpl<DecoderTypAdress, DecoderTypAdressModel> {

    public DecoderTypAdressMutator() {
        super(() -> new DecoderTypAdressModel(), new DecoderTypAdressTranscriber());
    }
}
