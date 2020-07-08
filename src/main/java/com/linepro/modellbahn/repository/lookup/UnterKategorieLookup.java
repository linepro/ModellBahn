package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.repository.UnterKategorieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Component(PREFIX + "UnterKategorieLookup")
public class UnterKategorieLookup {
    
    @Autowired
    private final UnterKategorieRepository repository;

    public UnterKategorie find(String kategorie, String unterKategorie) {
        if (StringUtils.hasText(kategorie) && StringUtils.hasText(unterKategorie)) {
            return repository.findByName(kategorie, unterKategorie)
                             .orElse(null);
        }
        
        return null;
    }
}
