package com.linepro.modellbahn.repository.lookup;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.UnterKategorieModel;
import com.linepro.modellbahn.repository.UnterKategorieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Component(PREFIX + "UnterKategorieLookup")
public class UnterKategorieLookup implements Lookup<UnterKategorie, UnterKategorieModel> {

    @Autowired
    private final UnterKategorieRepository repository;

    public Optional<UnterKategorie> find(String kategorie, String unterKategorie) {
        return repository.findByName(kategorie, unterKategorie);
    }

    @Override
    public Optional<UnterKategorie> find(UnterKategorie item) {
        if (item != null && item.getKategorie() != null && StringUtils.hasText(item.getKategorie().getName()) && StringUtils.hasText(item.getName())) {
            return find(item.getKategorie().getName(), item.getName());
        }

        return Optional.empty();
    }

    @Override
    public Optional<UnterKategorie> find(UnterKategorieModel item) {
        if (item != null && StringUtils.hasText(item.getKategorie()) && StringUtils.hasText(item.getName())) {
            return find(item.getKategorie(), item.getName());
        }

        return Optional.empty();
    }
}
