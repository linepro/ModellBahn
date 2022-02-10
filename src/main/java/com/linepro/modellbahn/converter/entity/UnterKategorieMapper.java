package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.UnterKategorieTranscriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;

@Component(PREFIX + "UnterKategorieMapper")
public class UnterKategorieMapper extends MapperImpl<UnterKategorie, UnterKategorieModel> {

    public UnterKategorieMapper(UnterKategorieTranscriber transcriber) {
        super(() -> new UnterKategorieModel(), transcriber);
    }
}
