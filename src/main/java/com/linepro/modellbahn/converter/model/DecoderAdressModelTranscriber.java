package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;

public class DecoderAdressModelTranscriber implements Transcriber<DecoderAdressModel,DecoderAdress> {
    public DecoderAdress apply(DecoderAdressModel source, DecoderAdress destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
