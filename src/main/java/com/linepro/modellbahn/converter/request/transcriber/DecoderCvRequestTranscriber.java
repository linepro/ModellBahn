package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.repository.lookup.DecoderTypCvLookup;
import com.linepro.modellbahn.request.DecoderCvRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "DecoderCvRequestTranscriber")
public class DecoderCvRequestTranscriber implements Transcriber<DecoderCvRequest, DecoderCv> {

    private final DecoderLookup decoderLookup;

    private final DecoderTypCvLookup cvLookup;

    @Override
    public DecoderCv apply(DecoderCvRequest source, DecoderCv destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoder() == null) {
                decoderLookup.find(source.getDecoderId()).ifPresent(d -> destination.setDecoder(d));
            }

            if (destination.getDecoder() != null && destination.getCv() == null) {
                cvLookup.find(destination.getDecoder().getDecoderTyp(), source.getCv()).ifPresent(c -> destination.setCv(c));
            }

            destination.setWert(source.getWert());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
