package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.repository.KategorieRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.request.UnterKategorieRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UnterKategorieRequestTranscriber extends NamedRequestTranscriber<UnterKategorieRequest, UnterKategorie> implements Transcriber<UnterKategorieRequest, UnterKategorie> {

    private final KategorieRepository kategorieRepository;

    private final ItemLookup lookup;

    @Override
    public UnterKategorie apply(UnterKategorieRequest source, UnterKategorie destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getKategorie() == null) {
                destination.setKategorie(lookup.find(source.getKategorie(), kategorieRepository));
            }
        }

        return super.apply(source, destination);
    }

}
