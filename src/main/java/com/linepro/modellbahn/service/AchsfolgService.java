package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class AchsfolgService extends AbstractNamedItemService<AchsfolgModel,Achsfolg> {

    @Autowired
    public AchsfolgService(AchsfolgRepository persister) {
        super(persister);
    }
}
