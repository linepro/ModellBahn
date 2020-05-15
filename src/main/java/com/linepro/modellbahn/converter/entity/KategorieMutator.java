package com.linepro.modellbahn.converter.entity;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;

@Component
public class KategorieMutator extends MutatorImpl<Kategorie, KategorieModel> {

    private UnterKategorieMutator unterKategorieMutator;

    @Autowired
    public KategorieMutator(UnterKategorieMutator unterKategorieMutator) {
        super(() -> new KategorieModel(), new NamedTranscriber<>());
        
        this.unterKategorieMutator = unterKategorieMutator;
    }

    public KategorieModel apply(Kategorie source, KategorieModel destination, int depth) {
        destination = super.apply(source, destination);
        destination.setUnterKategorien(source.getUnterKategorien()
                                             .stream()
                                             .sorted()
                                             .map(u -> unterKategorieMutator.convert(u))
                                             .collect(Collectors.toList()));
        return destination;
    }
}
