package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.AnderungRequestTranscriber;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.repository.lookup.ArtikelLookup;
import com.linepro.modellbahn.request.AnderungRequest;

@Component(PREFIX + "AnderungRequestMapper")
public class AnderungRequestMapper extends MapperImpl<AnderungRequest, Anderung> {

    @Autowired
    public AnderungRequestMapper(ArtikelLookup artikelLookup) {
        super(() -> new Anderung(), new AnderungRequestTranscriber(artikelLookup));
    }
}
