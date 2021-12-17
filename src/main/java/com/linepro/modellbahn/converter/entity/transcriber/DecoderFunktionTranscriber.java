package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;

public class DecoderFunktionTranscriber implements Transcriber<DecoderFunktion,DecoderFunktionModel> {

    @Override
    public DecoderFunktionModel apply(DecoderFunktion source, DecoderFunktionModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setDecoderId(source.getDecoder().getDecoderId());
            destination.setFunktion(source.getFunktion().getFunktion());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setProgrammable(source.getFunktion().getProgrammable());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
