package com.linepro.modellbahn.converter.entity.transcriber;

import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

public class BahnverwaltungTranscriber extends NamedTranscriber<Bahnverwaltung, BahnverwaltungModel> {
    @Override
    public BahnverwaltungModel apply(Bahnverwaltung source, BahnverwaltungModel destination) {
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
