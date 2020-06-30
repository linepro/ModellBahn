package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;

@Component(PREFIX + "KategorieMutator")
public class KategorieMutator extends MutatorImpl<Kategorie, KategorieModel> {

    private UnterKategorieMutator unterKategorieMutator;

    @Autowired
    public KategorieMutator(UnterKategorieMutator unterKategorieMutator) {
        super(() -> new KategorieModel(), new NamedTranscriber<>());
        
        this.unterKategorieMutator = unterKategorieMutator;
    }

    public KategorieModel apply(Kategorie source, KategorieModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination = super.apply(source, destination);
            
            if (isAvailable(source.getUnterKategorien())) {
                destination.setUnterKategorien(source.getUnterKategorien()
                                                     .stream()
                                                     .sorted()
                                                     .map(u -> attempt(() -> unterKategorieMutator.convert(u)))
                                                     .collect(success())
                                                     .orElseThrow());
            }
        }
        
        return destination;
    }
}
