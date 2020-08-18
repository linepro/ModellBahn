package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

public class DecoderTypFunktionModelTranscriber implements Transcriber<DecoderTypFunktionModel, DecoderTypFunktion> {
    
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
}
