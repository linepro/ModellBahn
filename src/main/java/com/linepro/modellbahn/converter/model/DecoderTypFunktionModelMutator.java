package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

@Component(PREFIX + "DecoderTypFunktionModelMutator")
public class DecoderTypFunktionModelMutator implements Mutator<DecoderTypFunktionModel, DecoderTypFunktion> {
    
    @Override
    public DecoderTypFunktion apply(DecoderTypFunktionModel source, DecoderTypFunktion destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setReihe(source.getReihe());
            destination.setFunktion(source.getFunktion());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setProgrammable(source.getProgrammable());
        }

        return destination;
    }

    @Override
    public DecoderTypFunktion get() {
        return new DecoderTypFunktion();
    }
}
