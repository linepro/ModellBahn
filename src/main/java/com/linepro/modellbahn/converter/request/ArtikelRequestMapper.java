package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.ArtikelRequestTranscriber;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.request.ArtikelRequest;

@Component(PREFIX + "ArtikelRequestMapper")
public class ArtikelRequestMapper extends MapperImpl<ArtikelRequest, Artikel> {

    @Autowired
    public ArtikelRequestMapper(ArtikelRequestTranscriber transcriber) {
        super(() -> new Artikel(), transcriber);
    }

}
