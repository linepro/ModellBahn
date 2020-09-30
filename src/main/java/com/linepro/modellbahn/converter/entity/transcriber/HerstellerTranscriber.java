package com.linepro.modellbahn.converter.entity.transcriber;

import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

public class HerstellerTranscriber extends NamedTranscriber<Hersteller, HerstellerModel> {
    @Override
    public HerstellerModel apply(Hersteller source, HerstellerModel destination) {
        destination.setUrl(source.getUrl());
        destination.setTelefon(source.getTelefon());
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
