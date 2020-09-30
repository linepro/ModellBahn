package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.DecoderAdressTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;

@Component(PREFIX + "DecoderAdressMutator")
public class DecoderAdressMutator extends MutatorImpl<DecoderAdress, DecoderAdressModel> {

    public DecoderAdressMutator() {
        super(() -> new DecoderAdressModel(), new DecoderAdressTranscriber());
    }
}
