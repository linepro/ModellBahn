package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

public class DecoderTypAdressTranscriber implements Transcriber<DecoderTypAdress,DecoderTypAdressModel> {
    public DecoderTypAdressModel apply(DecoderTypAdress source, DecoderTypAdressModel destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
