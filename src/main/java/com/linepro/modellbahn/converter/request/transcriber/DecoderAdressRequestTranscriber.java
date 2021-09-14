package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.repository.lookup.DecoderTypAdressLookup;
import com.linepro.modellbahn.request.DecoderAdressRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderAdressRequestTranscriber implements Transcriber<DecoderAdressRequest,DecoderAdress> {

    private final DecoderLookup decoderLookup;

    private final DecoderTypAdressLookup adressLookup;

    @Override
    public DecoderAdress apply(DecoderAdressRequest source, DecoderAdress destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoder() == null) {
                decoderLookup.find(source.getDecoderId()).ifPresent(d -> destination.setDecoder(d));
            }

            if (destination.getDecoder() != null && destination.getTyp() == null) {
                adressLookup.find(destination.getDecoder().getDecoderTyp(), source.getIndex()).ifPresent(a -> destination.setTyp(a));
            }

            destination.setAdress(source.getAdress());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
