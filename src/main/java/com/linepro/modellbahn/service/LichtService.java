package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.model.LichtModel;
import com.linepro.modellbahn.repository.LichtRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * LichtService. CRUD service for Licht
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class LichtService extends AbstractNamedItemService<LichtModel,Licht> {

    @Autowired
    public LichtService(LichtRepository persister) {
        super(persister);
    }
}
