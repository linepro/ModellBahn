package com.linepro.modellbahn.repository.lookup;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.repository.ArtikelRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ArtikelLookup {

    @Autowired
    private final ArtikelRepository repository;
    
    public Artikel find(ArtikelModel artikel) {
        return Optional.ofNullable(artikel)
                       .flatMap(m -> repository.findByArtikelId(m.getArtikelId()))
                       .orElse(null);
    }
}
