package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.linepro.modellbahn.controller.impl.AcceptableMediaTypes;
import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.controller.impl.FileService;
import com.linepro.modellbahn.converter.entity.ArtikelTranscriber;
import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.model.ArtikelModelTranscriber;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.model.ArtikelModel;
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

    @Autowired
    public ArtikelService(ArtikelRepository repository, FileService fileService) {
        super(repository, 
              new MutatorImpl<ArtikelModel, Artikel>(() -> new Artikel(), new ArtikelModelTranscriber()),
              new MutatorImpl<Artikel, ArtikelModel>(() -> new ArtikelModel(), new ArtikelTranscriber())
              );
        this.repository = repository;
        this.fileService = fileService;
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

    public Optional<AnderungModel> addAnderung(String artikelId, AnderungModel anderungModel) {
        // TODO Auto-generated method stub
        return null;
    }

    public Optional<AnderungModel> updateAnderung(String artikelId, Integer anderungId, AnderungModel anderungModel) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean deleteAnderung(String artikelId, Integer anderungId) {
        // TODO Auto-generated method stub
        return false;
    }
}
