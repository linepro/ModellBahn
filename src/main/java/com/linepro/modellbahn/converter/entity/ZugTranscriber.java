package com.linepro.modellbahn.converter.entity;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;

public class ZugTranscriber implements Transcriber<Zug,ZugModel> {
    public ZugModel apply(Zug source, ZugModel destination) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        return destination;
    }
}
