package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.repository.lookup.DecoderTypLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoderTypAdressModelTranscriber implements Transcriber<DecoderTypAdressModel,DecoderTypAdress> {

    private final DecoderTypLookup typLookup;

    @Override
    public DecoderTypAdress apply(DecoderTypAdressModel source, DecoderTypAdress destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getDecoderTyp() == null) {
                destination.setDecoderTyp(typLookup.find(source.getHersteller(), source.getBestellNr()));
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
