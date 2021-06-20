package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.UnterKategorieTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;

@Component(PREFIX + "UnterKategorieMutator")
public class UnterKategorieMutator extends MutatorImpl<UnterKategorie, UnterKategorieModel> {

    public UnterKategorieMutator() {
        super(() -> new UnterKategorieModel(), new UnterKategorieTranscriber());
    }
}
