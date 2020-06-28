package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;

@Component("DecoderFunktionMutator")
public class DecoderFunktionMutator implements Mutator<DecoderFunktion,DecoderFunktionModel> {

    @Override
    public DecoderFunktionModel apply(DecoderFunktion source, DecoderFunktionModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setDecoderId(source.getDecoder().getDecoderId());
            destination.setReihe(source.getFunktion().getReihe());
            destination.setFunktion(source.getFunktion().getFunktion());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setProgrammable(source.getFunktion().getProgrammable());
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public DecoderFunktionModel get() {
        return new DecoderFunktionModel();
    }
}
