package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.ArtikelTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;

@Component(PREFIX + "ArtikelMapper")
public class ArtikelMapper extends MapperImpl<Artikel, ArtikelModel> {

    @Autowired
    public ArtikelMapper(ArtikelTranscriber transcriber) {
        super(() -> new ArtikelModel(), transcriber);
    }

}
