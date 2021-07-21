package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.repository.DecoderTypAdressRepository;
import com.linepro.modellbahn.repository.lookup.DecoderLookup;
import com.linepro.modellbahn.request.DecoderAdressRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderAdressRequestTranscriber implements Transcriber<DecoderAdressRequest,DecoderAdress> {

    private final DecoderLookup decoderLookup;

    private final DecoderTypAdressRepository adressLookup;

    @Override
    public DecoderAdress apply(DecoderAdressRequest source, DecoderAdress destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoder() == null) {
                destination.setDecoder(decoderLookup.find(source.getDecoderId()));
            }

            if (destination.getDecoder() != null && destination.getTyp() == null) {
                destination.setTyp(adressLookup.findByIndex(destination.getDecoder().getDecoderTyp().getHersteller().getName(),
                                                            destination.getDecoder().getDecoderTyp().getBestellNr(), source.getIndex())
                                               .orElse(null));
            }

            destination.setAdress(source.getAdress());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
