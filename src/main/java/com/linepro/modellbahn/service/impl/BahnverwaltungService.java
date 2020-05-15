package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.BahnverwaltungMutator;
import com.linepro.modellbahn.converter.model.BahnverwaltungModelMutator;
import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * BahnverwaltungService. CRUD service for Bahnverwaltung
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class BahnverwaltungService extends NamedItemServiceImpl<BahnverwaltungModel,Bahnverwaltung> implements ItemService<BahnverwaltungModel> {

    @Autowired
    public BahnverwaltungService(BahnverwaltungRepository repository, BahnverwaltungModelMutator modelMutator, BahnverwaltungMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
