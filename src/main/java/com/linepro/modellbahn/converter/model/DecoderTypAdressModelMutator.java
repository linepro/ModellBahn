package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderTypAdressModelTranscriber;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;

@Component(PREFIX + "DecoderTypAdressModelMutator")
public class DecoderTypAdressModelMutator extends MutatorImpl<DecoderTypAdressModel, DecoderTypAdress> {

    @Autowired
    public DecoderTypAdressModelMutator(DecoderTypLookup lookup) {
        super(() -> new DecoderTypAdress(), new DecoderTypAdressModelTranscriber(lookup));
    }
}
