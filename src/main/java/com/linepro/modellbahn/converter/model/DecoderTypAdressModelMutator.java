package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

@Component
public class DecoderTypAdressModelMutator implements Mutator<DecoderTypAdressModel,DecoderTypAdress> {
    
    @Override
    public DecoderTypAdress apply(DecoderTypAdressModel source, DecoderTypAdress destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setPosition(source.getIndex());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setSpan(source.getSpan());
            destination.setAdressTyp(source.getAdressTyp());
            destination.setAdress(source.getWerkeinstellung());
        }

        return destination;
    }

    @Override
    public DecoderTypAdress get() {
        return new DecoderTypAdress();
    }
}
