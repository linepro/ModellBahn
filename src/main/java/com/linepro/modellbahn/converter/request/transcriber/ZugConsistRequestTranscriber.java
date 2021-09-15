package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.repository.lookup.ArtikelLookup;
import com.linepro.modellbahn.repository.lookup.ZugLookup;
import com.linepro.modellbahn.request.ZugConsistRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZugConsistRequestTranscriber implements Transcriber<ZugConsistRequest, ZugConsist> {

    private final ZugLookup lookup;

    private final ArtikelLookup artikelLookup;

    @Override
    public ZugConsist apply(ZugConsistRequest source, ZugConsist destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getZug() == null) {
                lookup.find(source.getZug()).ifPresent(z -> destination.setZug(z));
            }
            artikelLookup.find(source.getArtikelId()).ifPresent(a -> destination.setArtikel(a));
            destination.setPosition(source.getPosition());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
