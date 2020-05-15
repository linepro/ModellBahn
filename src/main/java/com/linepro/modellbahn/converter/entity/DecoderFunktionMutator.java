package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;

@Component
public class DecoderFunktionMutator implements Mutator<DecoderFunktion,DecoderFunktionModel> {

    public DecoderFunktionModel apply(DecoderFunktion source, DecoderFunktionModel destination, int depth) {
        destination.setReihe(source.getFunktion().getReihe());
        destination.setFunktion(source.getFunktion().getFunktion());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setProgrammable(source.getFunktion().getProgrammable());
        destination.setDeleted(source.getDeleted());
        return destination;
    }

    @Override
    public DecoderFunktionModel get() {
        return new DecoderFunktionModel();
    }
}
