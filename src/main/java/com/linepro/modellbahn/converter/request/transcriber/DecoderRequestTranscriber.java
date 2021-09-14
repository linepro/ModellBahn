package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.repository.lookup.ProtokollLookup;
import com.linepro.modellbahn.request.DecoderRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderRequestTranscriber implements Transcriber<DecoderRequest, Decoder> {

    private final DecoderTypLookup typLookup;

    private final ProtokollLookup protokollLookup;

    @Override
    public Decoder apply(DecoderRequest source, Decoder destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoderId() == null) {
                destination.setDecoderId(source.getDecoderId());
            }
            if (destination.getDecoderTyp() == null) {
                typLookup.find(source.getHersteller(), source.getBestellNr()).ifPresent(t -> destination.setDecoderTyp(t));
            }
            destination.setBezeichnung(source.getBezeichnung());
            protokollLookup.find(source.getProtokoll()).ifPresent(p -> destination.setProtokoll(p));
            destination.setFahrstufe(source.getFahrstufe());
            destination.setKaufdatum(source.getKaufdatum());
            destination.setWahrung(source.getWahrung());
            destination.setPreis(source.getPreis());
            destination.setAnmerkung(source.getAnmerkung());
            destination.setStatus(source.getStatus());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
