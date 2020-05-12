package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;

public class ZugConsistTranscriber implements Transcriber<ZugConsist,ZugConsistModel> {
    public ZugConsistModel apply(ZugConsist source, ZugConsistModel destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
