package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

public class DecoderTypAdressTranscriber implements Transcriber<DecoderTypAdress,DecoderTypAdressModel> {

    @Override
    public DecoderTypAdressModel apply(DecoderTypAdress source, DecoderTypAdressModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setHersteller(source.getDecoderTyp().getHersteller().getName());
            destination.setBestellNr(source.getDecoderTyp().getBestellNr());
            destination.setIndex(source.getPosition());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setSpan(source.getSpan());
            destination.setAdressTyp(source.getAdressTyp());
            destination.setWerkeinstellung(source.getAdress());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
