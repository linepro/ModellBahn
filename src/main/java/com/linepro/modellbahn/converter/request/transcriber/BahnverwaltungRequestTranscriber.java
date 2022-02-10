package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.request.BahnverwaltungRequest;

@Component(PREFIX + "BahnverwaltungRequestTranscriber")
public class BahnverwaltungRequestTranscriber extends NamedRequestTranscriber<BahnverwaltungRequest, Bahnverwaltung> {

    @Override
    public Bahnverwaltung apply(BahnverwaltungRequest source, Bahnverwaltung destination) {
        destination.setLand(source.getLand());

        return super.apply(source, destination);
    }
}
