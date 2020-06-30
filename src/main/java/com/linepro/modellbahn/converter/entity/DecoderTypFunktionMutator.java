package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

@Component(PREFIX + "DecoderTypFunktionMutator")
public class DecoderTypFunktionMutator implements Mutator<DecoderTypFunktion,DecoderTypFunktionModel> {

    @Override
    public DecoderTypFunktionModel apply(DecoderTypFunktion source, DecoderTypFunktionModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(source.getDecoderTyp().getHersteller().getName());
            destination.setBestellNr(source.getDecoderTyp().getBestellNr());
            destination.setReihe(source.getReihe());
            destination.setFunktion(source.getFunktion());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setProgrammable(source.getProgrammable());
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }

    @Override
    public DecoderTypFunktionModel get() {
        return new DecoderTypFunktionModel();
    }
}
