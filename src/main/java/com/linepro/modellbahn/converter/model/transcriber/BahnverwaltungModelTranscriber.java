package com.linepro.modellbahn.converter.model.transcriber;

import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;

public class BahnverwaltungModelTranscriber extends NamedModelTranscriber<BahnverwaltungModel, Bahnverwaltung> {
    @Override
    public Bahnverwaltung apply(BahnverwaltungModel source, Bahnverwaltung destination) {
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
