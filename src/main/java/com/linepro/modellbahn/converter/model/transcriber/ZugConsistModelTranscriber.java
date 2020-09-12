package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.repository.ZugRepository;
import com.linepro.modellbahn.repository.lookup.ArtikelLookup;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZugConsistModelTranscriber implements Transcriber<ZugConsistModel, ZugConsist> {

    private final ZugRepository zugRepository;

    private final ItemLookup lookup;

    private final ArtikelLookup artikelLookup;

    @Override
    public ZugConsist apply(ZugConsistModel source, ZugConsist destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getZug() == null) {
                destination.setZug(lookup.find(source.getZug(), zugRepository));
            }
            destination.setArtikel(artikelLookup.find(source.getArtikelId()));
            destination.setPosition(source.getPosition());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
