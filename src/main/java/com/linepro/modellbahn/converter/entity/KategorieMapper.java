package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.KategorieTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;

@Component(PREFIX + "KategorieMapper")
public class KategorieMapper extends MapperImpl<Kategorie, KategorieModel> {
    @Autowired
    public KategorieMapper(UnterKategorieMapper unterKategorieMapper) {
        super(() -> new KategorieModel(), new KategorieTranscriber(unterKategorieMapper));
    }
}
