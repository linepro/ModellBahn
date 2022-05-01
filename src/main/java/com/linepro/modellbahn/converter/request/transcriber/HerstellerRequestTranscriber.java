package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.request.HerstellerRequest;
import com.linepro.modellbahn.service.NameGenerator;

@Component(PREFIX + "HerstellerRequestTranscriber")
public class HerstellerRequestTranscriber extends NamedRequestTranscriber<HerstellerRequest, Hersteller> {

    public HerstellerRequestTranscriber(NameGenerator generator) {
        super(generator);
    }

    @Override
    public Hersteller apply(HerstellerRequest source, Hersteller destination) {
        destination.setUrl(source.getUrl());
        destination.setTelefon(source.getTelefon());
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
