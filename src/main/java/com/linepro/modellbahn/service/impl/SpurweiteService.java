package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.SpurweiteMutator;
import com.linepro.modellbahn.converter.model.SpurweiteModelMutator;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;
import com.linepro.modellbahn.repository.SpurweiteRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * SpurweiteService. CRUD service for Spurweite
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service("SpurweiteService")
public class SpurweiteService extends NamedItemServiceImpl<SpurweiteModel,Spurweite> implements ItemService<SpurweiteModel> {

    @Autowired
    public SpurweiteService(SpurweiteRepository repository, SpurweiteModelMutator modelMutator, SpurweiteMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
