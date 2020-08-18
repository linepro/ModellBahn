package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.entity.transcriber.KategorieTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;

@Component(PREFIX + "KategorieMutator")
public class KategorieMutator extends MutatorImpl<Kategorie, KategorieModel> {
    @Autowired
    public KategorieMutator(UnterKategorieMutator unterKategorieMutator) {
        super(() -> new KategorieModel(), new KategorieTranscriber(unterKategorieMutator));
    }
}
