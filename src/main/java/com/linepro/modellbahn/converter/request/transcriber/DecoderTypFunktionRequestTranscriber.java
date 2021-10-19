package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.request.DecoderTypFunktionRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderTypFunktionRequestTranscriber implements Transcriber<DecoderTypFunktionRequest, DecoderTypFunktion> {

    private final DecoderTypLookup typLookup;

    @Override
    public DecoderTypFunktion apply(DecoderTypFunktionRequest source, DecoderTypFunktion destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoderTyp() == null) {
                typLookup.find(source.getHersteller(), source.getBestellNr()).ifPresent(t -> destination.setDecoderTyp(t));
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
