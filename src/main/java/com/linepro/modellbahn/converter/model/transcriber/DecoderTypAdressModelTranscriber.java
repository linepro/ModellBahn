package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

public class DecoderTypAdressModelTranscriber implements Transcriber<DecoderTypAdressModel,DecoderTypAdress> {
    
    @Override
    public DecoderTypAdress apply(DecoderTypAdressModel source, DecoderTypAdress destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setPosition(source.getIndex());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setSpan(source.getSpan());
            destination.setAdressTyp(source.getAdressTyp());
            destination.setAdress(source.getWerkeinstellung());
        }

        return destination;
    }
}
