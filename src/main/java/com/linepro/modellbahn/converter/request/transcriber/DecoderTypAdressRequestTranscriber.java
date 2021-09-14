package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.request.DecoderTypAdressRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderTypAdressRequestTranscriber implements Transcriber<DecoderTypAdressRequest,DecoderTypAdress> {

    private final DecoderTypLookup typLookup;

    @Override
    public DecoderTypAdress apply(DecoderTypAdressRequest source, DecoderTypAdress destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoderTyp() == null) {
                typLookup.find(source.getHersteller(), source.getBestellNr()).ifPresent(t -> destination.setDecoderTyp(t));
            }
            if (destination.getPosition() == null) {
                destination.setPosition(source.getIndex());
            }
            destination.setBezeichnung(source.getBezeichnung());
            destination.setSpan(source.getSpan());
            destination.setAdressTyp(source.getAdressTyp());
            destination.setAdress(source.getWerkeinstellung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
