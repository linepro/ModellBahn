package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;
import com.linepro.modellbahn.util.exceptions.ResultCollector;

@Component
public class KategorieMutator extends MutatorImpl<Kategorie, KategorieModel> {

    private UnterKategorieMutator unterKategorieMutator;

    @Autowired
    public KategorieMutator(UnterKategorieMutator unterKategorieMutator) {
        super(() -> new KategorieModel(), new NamedTranscriber<>());
        
        this.unterKategorieMutator = unterKategorieMutator;
    }

    public KategorieModel apply(Kategorie source, KategorieModel destination, int depth) {
        destination = super.apply(source, destination, depth);
        
        if (depth >= 0) {
            if (source.getUnterKategorien() instanceof PersistentSet && ((PersistentSet) source.getUnterKategorien()).wasInitialized()) {
                destination.setUnterKategorien(source.getUnterKategorien()
                                                     .stream()
                                                     .sorted()
                                                     .map(u -> attempt(() -> unterKategorieMutator.convert(u)))
                                                     .collect(new ResultCollector<>())
                                                     .orElseThrow());
            }
        }

        return destination;
    }
}
