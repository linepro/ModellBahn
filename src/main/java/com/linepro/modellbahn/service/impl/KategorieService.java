package com.linepro.modellbahn.service.impl;

/**
 * KategorieService. CRUD service for Kategorie and UnterKategorie
 * Transpires that two way ManyToOne are best updated from the parent end
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.entity.KategorieMutator;
import com.linepro.modellbahn.converter.entity.UnterKategorieMutator;
import com.linepro.modellbahn.converter.model.KategorieModelMutator;
import com.linepro.modellbahn.converter.model.UnterKategorieModelMutator;
import com.linepro.modellbahn.entity.Kategorie;
import com.linepro.modellbahn.entity.UnterKategorie;
import com.linepro.modellbahn.model.KategorieModel;
import com.linepro.modellbahn.model.UnterKategorieModel;
import com.linepro.modellbahn.repository.KategorieRepository;
import com.linepro.modellbahn.repository.UnterKategorieRepository;
import com.linepro.modellbahn.service.ItemService;
import com.linepro.modellbahn.service.criterion.KategorieenCriteria;

@Service(PREFIX + "KategorieService")
public class KategorieService extends NamedItemServiceImpl<KategorieModel, Kategorie> implements ItemService<KategorieModel> {

    private final KategorieRepository repository;

    private final UnterKategorieRepository unterKategorieRepository;

    private final UnterKategorieMutator unterKategorieMutator;

    private final UnterKategorieModelMutator unterKategorieModelMutator;

    @Autowired
    public KategorieService(KategorieRepository repository, KategorieModelMutator modelMutator, KategorieMutator entityMutator, UnterKategorieRepository unterKategorieRepository, UnterKategorieMutator unterKategorieMutator, UnterKategorieModelMutator unterKategorieModelMutator) {
        super(repository, modelMutator, entityMutator);

        this.repository = repository;
        this.unterKategorieRepository = unterKategorieRepository;
        this.unterKategorieMutator = unterKategorieMutator;
        this.unterKategorieModelMutator = unterKategorieModelMutator;
     }

    @Transactional
    public Optional<UnterKategorieModel> addUnterKategorie(String kategorieStr, UnterKategorieModel model) {
        return repository.findByName(kategorieStr)
                         .map(k -> {
                             UnterKategorie unterKategorie = unterKategorieModelMutator.convert(model);
                             unterKategorie.setDeleted(false);
                             
                             k.addUnterKategorie(unterKategorie);
                             
                             repository.saveAndFlush(k);
                                
                             return (unterKategorieMutator.convert(unterKategorie));
                             });
    }

    @Transactional
    public Optional<UnterKategorieModel> updateUnterKategorie(String kategorieStr, String unterKategorieStr, UnterKategorieModel model) {
        return unterKategorieRepository.findByName(kategorieStr, unterKategorieStr)
                                       .map(u -> unterKategorieMutator.convert(unterKategorieRepository.saveAndFlush(unterKategorieModelMutator.apply(model, u))));
    }

    @Transactional
    public boolean deleteUnterKategorie(String kategorieStr, String unterKategorieStr) {
        return unterKategorieRepository.findByName(kategorieStr, unterKategorieStr)
                                       .map(u -> {
                                           unterKategorieRepository.delete(u);
                                           return true;
                                       })
                                       .orElse(false);
    }

    @Transactional(readOnly = true)
    public Page<KategorieModel> searchUnterKategorie(Optional<List<String>> kategorieen, Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        Pageable pageRequest = getPageRequest(pageNumber, pageSize);

        final Page<Kategorie> data = repository.findAll(new KategorieenCriteria(kategorieen), pageRequest);

        return getModelPage(data);
    }
}
