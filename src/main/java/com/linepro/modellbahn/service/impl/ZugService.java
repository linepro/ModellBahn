package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linepro.modellbahn.converter.entity.ZugConsistMutator;
import com.linepro.modellbahn.converter.entity.ZugMutator;
import com.linepro.modellbahn.converter.model.ZugModelMutator;
import com.linepro.modellbahn.entity.Artikel;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ArtikelRepository;
import com.linepro.modellbahn.repository.ZugConsistRepository;
import com.linepro.modellbahn.repository.ZugRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * ZugService. CRUD service for Zug
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class ZugService extends NamedItemServiceImpl<ZugModel,Zug> implements ItemService<ZugModel> {

    private final ZugRepository repository;
    
    private final  ArtikelRepository artikelRepository;
    
    private final ZugConsistRepository consistRepository;
    
    private final ZugConsistMutator consistMutator;
    
    @Autowired
    public ZugService(ZugRepository repository, ZugModelMutator modelMutator, ZugMutator entityMutator, ArtikelRepository artikelRepository, ZugConsistRepository consistRepository, ZugConsistMutator consistMutator) {
        super(repository, modelMutator, entityMutator);
        
        this.repository = repository;
        this.artikelRepository = artikelRepository;
        this.consistRepository = consistRepository;
        this.consistMutator = consistMutator;
    }

    @Transactional
    public Optional<ZugConsistModel> addConsist(String name, String artikelId) {
        Optional<Zug> zug = repository.findByName(name);
        Optional<Artikel> artikel = artikelRepository.findByArtikelId(artikelId);
        
        if (zug.isPresent() && artikel.isPresent()) {
            ZugConsist consist = new ZugConsist();

            consist.setZug(zug.get());
            consist.setPosition(zug.get().getConsist().size()+1);
            consist.setArtikel(artikel.get());
            consist.setDeleted(false);
            
            return Optional.of(consistMutator.convert(consistRepository.saveAndFlush(consist)));
        }
        
        return Optional.empty();
    }

    @Transactional
    public Optional<ZugConsistModel> updateConsist(String zugStr, Integer position, String artikelId) {
        Optional<ZugConsist> found = consistRepository.findByPosition(zugStr, position);
        Optional<Artikel> artikel = artikelRepository.findByArtikelId(artikelId);
        
        if (found.isPresent() && artikel.isPresent()) {
            ZugConsist consist = found.get();
            consist.setArtikel(artikel.get());
            
            return Optional.of(consistMutator.convert(consistRepository.saveAndFlush(consist)));
        }
        
        return Optional.empty();
    }

    @Transactional
    public boolean deleteConsist(String zugStr, Integer position) {
        return consistRepository.findByPosition(zugStr, position)
                                .map(c -> {
                                    consistRepository.delete(c);
                                    return true;    
                                })
                                .orElse(false);

    }
}
