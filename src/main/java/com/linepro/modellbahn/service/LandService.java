package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Land;
import com.linepro.modellbahn.model.LandModel;
import com.linepro.modellbahn.repository.LandRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * LandService. CRUD service for Land
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class LandService extends AbstractNamedItemService<LandModel,Land> {

    @Autowired
    public LandService(LandRepository persister) {
        super(persister);
    }
}
