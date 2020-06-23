package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

@Component
public class DecoderTypAdressMutator implements Mutator<DecoderTypAdress,DecoderTypAdressModel> {
    
    @Override
    public DecoderTypAdressModel apply(DecoderTypAdress source, DecoderTypAdressModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(source.getDecoderTyp().getHersteller().getName());
            destination.setBestellNr(source.getDecoderTyp().getBestellNr());
            destination.setIndex(source.getPosition());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setSpan(source.getSpan());
            destination.setAdressTyp(source.getAdressTyp());
            destination.setWerkeinstellung(source.getAdress());
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public DecoderTypAdressModel get() {
        return new DecoderTypAdressModel();
    }
}
