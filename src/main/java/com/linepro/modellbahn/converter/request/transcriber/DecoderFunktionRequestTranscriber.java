package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.repository.DecoderTypFunktionRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.request.DecoderFunktionRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderFunktionRequestTranscriber implements Transcriber<DecoderFunktionRequest, DecoderFunktion> {

    private final DecoderLookup decoderLookup;

    private final DecoderTypFunktionRepository funktionLookup;

    @Override
    public DecoderFunktion apply(DecoderFunktionRequest source, DecoderFunktion destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoder() == null) {
                destination.setDecoder(decoderLookup.find(source.getDecoderId()));
            }

            if (destination.getDecoder() != null && destination.getFunktion() == null) {
                    destination.setFunktion(funktionLookup.findByFunktion(destination.getDecoder().getDecoderTyp().getHersteller().getName(), 
                                                                          destination.getDecoder().getDecoderTyp().getBestellNr(), source.getReihe(), source.getFunktion())
                                                          .orElse(null));
            }

            destination.setBezeichnung(source.getBezeichnung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
