package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderModelTranscriber implements Transcriber<DecoderModel, Decoder> {

    private final DecoderTypLookup typLookup;

    private final ProtokollRepository protokollRepository;

    private final ItemLookup lookup;

    @Override
    public Decoder apply(DecoderModel source, Decoder destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoderId() == null) {
                destination.setDecoderId(source.getDecoderId());
            }
            if (destination.getDecoderTyp() == null) {
                destination.setDecoderTyp(typLookup.find(source.getHersteller(), source.getBestellNr()));
            }
            destination.setBezeichnung(source.getBezeichnung());
            destination.setProtokoll(lookup.find(source.getProtokoll(), protokollRepository));
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
