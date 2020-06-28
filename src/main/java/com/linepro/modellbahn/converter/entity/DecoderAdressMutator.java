package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;

@Component("DecoderAdressMutator")
public class DecoderAdressMutator implements Mutator<DecoderAdress,DecoderAdressModel> {

    @Override
    public DecoderAdressModel apply(DecoderAdress source, DecoderAdressModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setDecoderId(source.getDecoder().getDecoderId());
            destination.setIndex(source.getTyp().getPosition());
            destination.setBezeichnung(source.getTyp().getBezeichnung());
            destination.setSpan(source.getTyp().getSpan());
            destination.setAdressTyp(source.getTyp().getAdressTyp());
            destination.setWerkeinstellung(source.getTyp().getAdress());
            destination.setAdress(source.getAdress());
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public DecoderAdressModel get() {
        return new DecoderAdressModel();
    }
}
