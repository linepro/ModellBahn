package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.BahnverwaltungRequestTranscriber;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.request.BahnverwaltungRequest;

@Component(PREFIX + "BahnverwaltungRequestMapper")
public class BahnverwaltungRequestMapper extends MapperImpl<BahnverwaltungRequest, Bahnverwaltung> {

    @Autowired
    public BahnverwaltungRequestMapper(BahnverwaltungRequestTranscriber transcriber) {
        super(() -> new Bahnverwaltung(), transcriber);
    }
}
