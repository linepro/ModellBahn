package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;

@Component(PREFIX + "UnterKategorieMutator")
public class UnterKategorieMutator extends MutatorImpl<UnterKategorie, UnterKategorieModel> {

    public UnterKategorieMutator() {
        super(() -> new UnterKategorieModel(), new NamedTranscriber<UnterKategorie, UnterKategorieModel>());
    }

    @Override
    public UnterKategorieModel apply(UnterKategorie source, UnterKategorieModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setKategorie(source.getKategorie().getName());
        }
        
        return super.apply(source, destination);
    }
}
