package com.linepro.modellbahn.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.model.KategorieModel;
import com.linepro.modellbahn.model.UnterKategorieModel;
import com.linepro.modellbahn.repository.KategorieRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * KategorieService. CRUD service for Kategorie and UnterKategorie
 * Transpires that two way ManyToOne are best updated from the parent end
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class KategorieService extends NamedItemServiceImpl<KategorieModel, Kategorie> implements ItemService<KategorieModel> {

    @Autowired
    public KategorieService(KategorieRepository repository) {
        super(repository, () -> new KategorieModel(), () -> new Kategorie());
    }

    public Page<UnterKategorieModel> searchUnterKategorie(List<String> kategorieen, Map<String, String> arguments) {
        return Page.empty();
    }

    public Optional<UnterKategorieModel> addUnterKategorie(String kategorieStr, UnterKategorieModel unterKategorie) {
        return Optional.empty();
    }

    public Optional<UnterKategorieModel> updateUnterKategorie(String kategorieStr, String unterKategorieStr, UnterKategorieModel unterKategorie) {
        return Optional.empty();
    }

    public boolean deleteUnterKategorie(String kategorieStr, String unterKategorieStr) {
        return false;
    }

    public Page<UnterKategorieModel> searchUnterKategorie(List<String> kategorieen, UnterKategorieModel model) {
        return Page.empty();
    }
}
