package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;
import com.linepro.modellbahn.repository.AntriebRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * AntriebService. CRUD service for Antrieb
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class AntriebService extends AbstractNamedItemService<AntriebModel,Antrieb> {

    @Autowired
    public AntriebService(AntriebRepository persister) {
        super(persister);
    }
}
