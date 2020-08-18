package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

import com.linepro.modellbahn.converter.entity.UnterKategorieMutator;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KategorieTranscriber extends NamedTranscriber<Kategorie, KategorieModel> {

    private final UnterKategorieMutator unterKategorieMutator;

    @Override
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
