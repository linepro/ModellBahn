package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderTypFunktionModelTranscriber implements Transcriber<DecoderTypFunktionModel, DecoderTypFunktion> {

    private final DecoderTypLookup typLookup;

    @Override
    public DecoderTypFunktion apply(DecoderTypFunktionModel source, DecoderTypFunktion destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoderTyp() == null) {
                destination.setDecoderTyp(typLookup.find(source.getHersteller(), source.getBestellNr()));
            }
            if (destination.getReihe() == null) {
                destination.setReihe(source.getReihe());
            }
            if (destination.getFunktion() == null) {
                destination.setFunktion(source.getFunktion());
            }
            destination.setBezeichnung(source.getBezeichnung());
            destination.setProgrammable(source.getProgrammable());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
