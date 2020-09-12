package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;
import com.linepro.modellbahn.repository.KategorieRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UnterKategorieModelTranscriber extends NamedModelTranscriber<UnterKategorieModel, UnterKategorie> implements Transcriber<UnterKategorieModel, UnterKategorie> {

    private final KategorieRepository kategorieRepository;

    private final ItemLookup lookup;

    @Override
    public UnterKategorie apply(UnterKategorieModel source, UnterKategorie destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getKategorie() == null) {
                destination.setKategorie(lookup.find(source.getKategorie(), kategorieRepository));
            }
        }

        return super.apply(source, destination);
    }

}
