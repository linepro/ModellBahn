package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

@Component
public class DecoderTypFunktionModelMutator implements Mutator<DecoderTypFunktionModel, DecoderTypFunktion> {
    
    public DecoderTypFunktion apply(DecoderTypFunktionModel source, DecoderTypFunktion destination, int depth) {
        destination.setReihe(source.getReihe());
        destination.setFunktion(source.getFunktion());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setProgrammable(source.getProgrammable());
        destination.setDeleted(source.getDeleted());
        return destination;
    }

    @Override
    public DecoderTypFunktion get() {
        return new DecoderTypFunktion();
    }
}
