package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;

public class UnterKategorieTranscriber extends NamedTranscriber<UnterKategorie, UnterKategorieModel> {
    @Override
    public UnterKategorieModel apply(UnterKategorie source, UnterKategorieModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setKategorie(source.getKategorie().getName());
            destination.setKategorieBezeichnung(source.getKategorie().getBezeichnung());
        }

        return super.apply(source, destination);
    }
}
