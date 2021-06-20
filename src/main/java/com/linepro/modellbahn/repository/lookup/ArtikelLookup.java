package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.repository.ArtikelRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Component(PREFIX + "ArtikelLookup")
public class ArtikelLookup {

    @Autowired
    private final ArtikelRepository repository;

    public Artikel find(ArtikelModel artikel) {
        return Optional.ofNullable(artikel)
                       .map(m -> find(m.getArtikelId()))
                       .orElse(null);
    }

    public Artikel find(String artikelId) {
        return repository.findByArtikelId(artikelId)
                         .orElse(null);
    }
}
