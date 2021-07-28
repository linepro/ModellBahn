package com.linepro.modellbahn.converter.request.transcriber;

import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.request.BahnverwaltungRequest;

public class BahnverwaltungRequestTranscriber extends NamedRequestTranscriber<BahnverwaltungRequest, Bahnverwaltung> {
    @Override
    public Bahnverwaltung apply(BahnverwaltungRequest source, Bahnverwaltung destination) {
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
