package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.transcriber.DecoderAdressModelTranscriber;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.repository.DecoderTypAdressRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;

@Component(PREFIX + "DecoderAdressModelMutator")
public class DecoderAdressModelMutator extends MutatorImpl<DecoderAdressModel,DecoderAdress> {

    @Autowired
    public DecoderAdressModelMutator(DecoderLookup decoderLookup, DecoderTypAdressRepository adressLookup) {
        super(() -> new DecoderAdress(), new DecoderAdressModelTranscriber(decoderLookup, adressLookup));
    }
}
