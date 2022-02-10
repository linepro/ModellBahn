package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

@Component(PREFIX + "BahnverwaltungTranscriber")
public class BahnverwaltungTranscriber extends NamedAbbildungTranscriber<Bahnverwaltung, BahnverwaltungModel> {

    public BahnverwaltungTranscriber(PathMapper pathMapper) {
        super(pathMapper);
    }

    @Override
    public BahnverwaltungModel apply(Bahnverwaltung source, BahnverwaltungModel destination) {
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
