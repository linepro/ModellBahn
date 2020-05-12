package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;

public class DecoderAdressTranscriber implements Transcriber<DecoderAdress,DecoderAdressModel> {
    public DecoderAdressModel apply(DecoderAdress source, DecoderAdressModel destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
