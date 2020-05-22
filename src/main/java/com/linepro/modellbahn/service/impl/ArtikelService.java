package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.converter.entity.AnderungMutator;
import com.linepro.modellbahn.converter.entity.ArtikelMutator;
import com.linepro.modellbahn.converter.model.AnderungModelMutator;
import com.linepro.modellbahn.converter.model.ArtikelModelMutator;
import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.io.FileService;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.repository.AnderungRepository;
import com.linepro.modellbahn.repository.ArtikelRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * ArtikelService. CRUD service for Artikel
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class ArtikelService extends ItemServiceImpl<ArtikelModel, Artikel> implements ItemService<ArtikelModel> {

    private final ArtikelRepository repository;

    private final FileService fileService;

    private final AnderungRepository anderungRepository;
    
    private final AnderungMutator anderungMutator;
    
    private final AnderungModelMutator anderungModelMutator;

    @Autowired
    public ArtikelService(ArtikelRepository repository, ArtikelModelMutator modelMutator, ArtikelMutator entityMutator, FileService fileService,
                    AnderungRepository anderungRepository, AnderungMutator anderungMutator, AnderungModelMutator anderungModelMutator) {
        super(repository, modelMutator, entityMutator);
        
        this.repository = repository;
        this.fileService = fileService;
        
        this.anderungRepository = anderungRepository;
        this.anderungMutator = anderungMutator;
        this.anderungModelMutator = anderungModelMutator;
    }

    public Optional<ArtikelModel> get(String artikelId) {
        return super.get(() -> repository.findByArtikelId(artikelId));
    }

    public Optional<ArtikelModel> update(String artikelId, ArtikelModel artikelModel) {
        return super.update(() -> repository.findByArtikelId(artikelId), artikelModel);
    }

    public boolean delete(String artikelId) {
        return super.delete(() -> repository.findByArtikelId(artikelId));
    }

    @Transactional
    public Optional<ArtikelModel> updateAbbildung(String artikelId, MultipartFile multipart) {
        return  repository.findByArtikelId(artikelId)
                         .map(a -> {
                             a.setAbbildung(fileService.updateFile(AcceptableMediaTypes.IMAGE_TYPES, multipart, ApiNames.ARTIKEL, ApiNames.ABBILDUNG, artikelId));
                             return entityMutator.convert(a);
                             });
    }

    @Transactional
    public Optional<ArtikelModel> deleteAbbildung(String artikelId) {
        return repository.findByArtikelId(artikelId)
                         .map(a -> {
                            a.setAbbildung(fileService.deleteFile(a.getAbbildung()));
                            return entityMutator.convert(a);
                            });
    }

    @Transactional
    public Optional<AnderungModel> addAnderung(String artikelId, AnderungModel anderungModel) {
        return repository.findByArtikelId(artikelId)
                         .map(a -> {
                            Anderung anderung = anderungModelMutator.convert(anderungModel);
                            anderung.setDeleted(false);

                            a.addAnderung(anderung);
            
                            repository.saveAndFlush(a);
                            
                            return anderungMutator.convert(anderung);
                        });
    }

    @Transactional
    public Optional<AnderungModel> updateAnderung(String artikelId, Integer anderungId, AnderungModel anderungModel) {
        return anderungRepository.findByAnderungId(artikelId, anderungId)
                                 .map(a -> anderungMutator.convert(anderungRepository.saveAndFlush(anderungModelMutator.apply(anderungModel, a))));
    }

    @Transactional
    public boolean deleteAnderung(String artikelId, Integer anderungId) {
        return anderungRepository.findByAnderungId(artikelId, anderungId)
                                 .map(a -> {
                                     anderungRepository.delete(a);
                                     return true;
                                 })
                                 .orElse(false);
    }
}
