package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;

public class DecoderTypAdressModelTranscriber implements Transcriber<DecoderTypAdressModel,DecoderTypAdress> {
    public DecoderTypAdress apply(DecoderTypAdressModel source, DecoderTypAdress destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
