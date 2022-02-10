package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;

@Component(PREFIX + "UnterKategorieTranscriber")
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
