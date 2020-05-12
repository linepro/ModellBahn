package com.linepro.modellbahn.converter.model;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;

public class ZugConsistModelTranscriber implements Transcriber<ZugConsistModel,ZugConsist> {
    public ZugConsist apply(ZugConsistModel source, ZugConsist destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
