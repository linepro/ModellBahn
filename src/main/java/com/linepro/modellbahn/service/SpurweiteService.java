package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;
import com.linepro.modellbahn.repository.SpurweiteRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * SpurweiteService. CRUD service for Spurweite
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class SpurweiteService extends AbstractNamedItemService<SpurweiteModel,Spurweite> {

    @Autowired
    public SpurweiteService(SpurweiteRepository persister) {
        super(persister);
    }
}
