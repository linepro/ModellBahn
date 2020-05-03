package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.model.ArtikelModel;
import com.linepro.modellbahn.repository.ArtikelRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * ArtikelService. CRUD service for Artikel
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class ArtikelService extends AbstractItemService<ArtikelModel, Artikel> {

    private final ArtikelRepository persister;

    @Autowired
    public ArtikelService(ArtikelRepository persister) {
        super(persister);
        
        this.persister = persister;
    }

    @Override
    protected Optional<Artikel> findByModel(ArtikelModel model) {
        return persister.findByArtikelId(model.getArtikelId());
    }
}
