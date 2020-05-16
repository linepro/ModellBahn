package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

@Component
public class DecoderTypAdressModelMutator implements Mutator<DecoderTypAdressModel,DecoderTypAdress> {
    
    public DecoderTypAdress apply(DecoderTypAdressModel source, DecoderTypAdress destination, int depth) {
        destination.setIndex(source.getIndex());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setSpan(source.getSpan());
        destination.setAdressTyp(source.getAdressTyp());
        destination.setAdress(source.getWerkeinstellung());
        return destination;
    }

    @Override
    public DecoderTypAdress get() {
        return new DecoderTypAdress();
    }
}
