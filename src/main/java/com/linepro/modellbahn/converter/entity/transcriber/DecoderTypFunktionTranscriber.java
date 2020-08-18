package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;

public class DecoderTypFunktionTranscriber implements Transcriber<DecoderTypFunktion,DecoderTypFunktionModel> {

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
}
