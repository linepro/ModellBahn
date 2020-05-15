package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;

@Component
public class UnterKategorieMutator extends MutatorImpl<UnterKategorie, UnterKategorieModel> {

    public UnterKategorieMutator() {
        super(() -> new UnterKategorieModel(), new NamedTranscriber<UnterKategorie, UnterKategorieModel>());
    }

    @Override
    public UnterKategorieModel apply(UnterKategorie source, UnterKategorieModel destination, int depth) {
        destination.setKategorie(source.getKategorie().getName());
        return super.apply(source, destination, depth);
    }

}
