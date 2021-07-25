package com.linepro.modellbahn.converter.entity.transcriber;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

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
