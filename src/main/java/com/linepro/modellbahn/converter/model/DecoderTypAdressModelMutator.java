package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderTypAdressModelTranscriber;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

@Component(PREFIX + "DecoderTypAdressModelMutator")
public class DecoderTypAdressModelMutator extends MutatorImpl<DecoderTypAdressModel, DecoderTypAdress> {

    public DecoderTypAdressModelMutator() {
        super(() -> new DecoderTypAdress(), new DecoderTypAdressModelTranscriber());
    }
}
