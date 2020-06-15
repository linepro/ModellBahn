package com.linepro.modellbahn.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

/**
 * KategorieService. CRUD service for Kategorie and UnterKategorie
 * Transpires that two way ManyToOne are best updated from the parent end
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class KategorieService extends NamedItemServiceImpl<KategorieModel, Kategorie> implements ItemService<KategorieModel> {

    private final KategorieRepository repository;
    
    private final UnterKategorieRepository unterKategorieRepository;
    
    private final UnterKategorieMutator unterKategorieMutator;

    private final UnterKategorieModelMutator unterKategorieModelMutator;

    private final KategorieMutator mutator;
    
    @Autowired
    public KategorieService(KategorieRepository repository, KategorieModelMutator modelMutator, KategorieMutator entityMutator, UnterKategorieRepository unterKategorieRepository, UnterKategorieMutator unterKategorieMutator, UnterKategorieModelMutator unterKategorieModelMutator) {
        super(repository, modelMutator, entityMutator);

        this.repository = repository;
        this.mutator = entityMutator;
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
                                       .map(u -> unterKategorieMutator.convert(unterKategorieRepository.saveAndFlush(unterKategorieModelMutator.applyFields(model, u))));
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
    public Page<UnterKategorieModel> searchUnterKategorie(Optional<List<String>> kategorieen, Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber.orElse(FIRST_PAGE), pageSize.orElse(DEFAULT_PAGE_SIZE));
        
        final List<UnterKategorieModel> unterkategorien = repository.findKategorien(kategorieen)
                                                                    .stream()
                                                                    .map(e -> mutator.convert(e))
                                                                    .map(k -> k.getUnterKategorien())
                                                                    .flatMap(List::stream)
                                                                    .collect(Collectors.toList());
        
        return new PageImpl<UnterKategorieModel>(unterkategorien,
                                                    pageRequest,
                                                    unterkategorien.size()
                                                    );
    }
}
