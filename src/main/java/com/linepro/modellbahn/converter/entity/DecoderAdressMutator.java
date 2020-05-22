package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;

@Component
public class DecoderAdressMutator implements Mutator<DecoderAdress,DecoderAdressModel> {

    public DecoderAdressModel apply(DecoderAdress source, DecoderAdressModel destination, int depth) {
        destination.setDecoderId(source.getDecoder().getDecoderId());
        destination.setIndex(source.getTyp().getIndex());
        destination.setBezeichnung(source.getTyp().getBezeichnung());
        destination.setSpan(source.getTyp().getSpan());
        destination.setAdressTyp(source.getTyp().getAdressTyp());
        destination.setWerkeinstellung(source.getTyp().getAdress());
        destination.setAdress(source.getAdress());
        destination.setDeleted(source.getDeleted());
        return destination;
    }

    @Override
    public DecoderAdressModel get() {
        return new DecoderAdressModel();
    }
}
