package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.repository.ArtikelRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ArtikelLookup")
public class ArtikelLookup implements Lookup<Artikel, ArtikelModel> {

    private final ArtikelRepository repository;

    public Optional<Artikel> find(String artikelId) {
        if (StringUtils.hasText(artikelId)) {
            return repository.findByArtikelId(artikelId);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Artikel> find(ArtikelModel model) {
        if (model != null) {
            return find(model.getArtikelId());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Artikel> find(Artikel artikel) {
        if (artikel != null) {
            return find(artikel.getArtikelId());
        }

        return Optional.empty();
    }
}
