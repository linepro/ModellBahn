package com.linepro.modellbahn.converter.model.transcriber;

import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

public class HerstellerModelTranscriber extends NamedModelTranscriber<HerstellerModel, Hersteller> {
    @Override
    public Hersteller apply(HerstellerModel source, Hersteller destination) {
        destination.setUrl(source.getUrl());
        destination.setTelefon(source.getTelefon());
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
