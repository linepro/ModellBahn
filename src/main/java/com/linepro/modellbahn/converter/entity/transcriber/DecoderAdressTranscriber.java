package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;

public class DecoderAdressTranscriber implements Transcriber<DecoderAdress,DecoderAdressModel> {

    @Override
    public DecoderAdressModel apply(DecoderAdress source, DecoderAdressModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setDecoderId(source.getDecoder().getDecoderId());
            destination.setIndex(source.getTyp().getPosition());
            destination.setBezeichnung(source.getTyp().getBezeichnung());
            destination.setSpan(source.getTyp().getSpan());
            destination.setAdressTyp(source.getTyp().getAdressTyp());
            destination.setWerkeinstellung(source.getTyp().getAdress());
            destination.setAdress(source.getAdress());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }
        
        return destination;
    }
}
