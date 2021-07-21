package com.linepro.modellbahn.converter.request.transcriber;

import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.request.HerstellerRequest;

public class HerstellerRequestTranscriber extends NamedRequestTranscriber<HerstellerRequest, Hersteller> {
    @Override
    public Hersteller apply(HerstellerRequest source, Hersteller destination) {
        destination.setUrl(source.getUrl());
        destination.setTelefon(source.getTelefon());
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
