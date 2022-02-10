package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;

@Component(PREFIX + "HerstellerTranscriber")
public class HerstellerTranscriber extends NamedAbbildungTranscriber<Hersteller, HerstellerModel> {

    public HerstellerTranscriber(PathMapper pathMapper) {
        super(pathMapper);
    }

    @Override
    public HerstellerModel apply(Hersteller source, HerstellerModel destination) {
        destination.setUrl(source.getUrl());
        destination.setTelefon(source.getTelefon());
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
