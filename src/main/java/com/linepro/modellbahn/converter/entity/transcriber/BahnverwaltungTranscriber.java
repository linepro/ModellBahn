package com.linepro.modellbahn.converter.entity.transcriber;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

public class BahnverwaltungTranscriber extends NamedAbbildungTranscriber<Bahnverwaltung, BahnverwaltungModel> {

    public BahnverwaltungTranscriber(PathMutator pathMutator) {
        super(pathMutator);
    }

    @Override
    public BahnverwaltungModel apply(Bahnverwaltung source, BahnverwaltungModel destination) {
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
