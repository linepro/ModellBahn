package com.linepro.modellbahn.converter.model;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

@Component
public class DecoderTypFunktionModelMutator implements Mutator<DecoderTypFunktionModel, DecoderTypFunktion> {
    
    @Override
    public DecoderTypFunktion apply(DecoderTypFunktionModel source, DecoderTypFunktion destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setReihe(source.getReihe());
            destination.setFunktion(source.getFunktion());
        }

        return applyFields(source, destination);
    }

    @Override
    public DecoderTypFunktion applyFields(DecoderTypFunktionModel source, DecoderTypFunktion destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setBezeichnung(source.getBezeichnung());
            destination.setProgrammable(source.getProgrammable());
            destination.setDeleted(source.getDeleted());
        }

        return destination;
    }

    @Override
    public DecoderTypFunktion get() {
        return new DecoderTypFunktion();
    }
}
