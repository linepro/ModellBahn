package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.repository.lookup.KategorieLookup;
import com.linepro.modellbahn.request.UnterKategorieRequest;
import com.linepro.modellbahn.service.NameGenerator;

@Component(PREFIX + "UnterKategorieRequestTranscriber")
public class UnterKategorieRequestTranscriber extends NamedRequestTranscriber<UnterKategorieRequest, UnterKategorie> implements Transcriber<UnterKategorieRequest, UnterKategorie> {

    private final KategorieLookup lookup;

    public UnterKategorieRequestTranscriber(NameGenerator generator, KategorieLookup lookup) {
        super(generator);
        this.lookup = lookup;
    }

    @Override
    public UnterKategorie apply(UnterKategorieRequest source, UnterKategorie destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getKategorie() == null) {
                lookup.find(source.getKategorie()).ifPresent(k -> destination.setKategorie(k));
            }
        }

        return super.apply(source, destination);
    }

}
