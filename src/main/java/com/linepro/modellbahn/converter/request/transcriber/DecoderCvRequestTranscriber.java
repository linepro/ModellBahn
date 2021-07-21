package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.repository.DecoderTypCvRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.request.DecoderCvRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderCvRequestTranscriber implements Transcriber<DecoderCvRequest, DecoderCv> {

    private final DecoderLookup decoderLookup;

    private final DecoderTypCvRepository cvLookup;

    @Override
    public DecoderCv apply(DecoderCvRequest source, DecoderCv destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoder() == null) {
                destination.setDecoder(decoderLookup.find(source.getDecoderId()));
            }

            if (destination.getDecoder() != null && destination.getCv() == null) {
                destination.setCv(cvLookup.findByCv(destination.getDecoder().getDecoderTyp().getHersteller().getName(),
                                                    destination.getDecoder().getDecoderTyp().getBestellNr(), source.getCv())
                                          .orElse(null));
            }

            destination.setWert(source.getWert());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
