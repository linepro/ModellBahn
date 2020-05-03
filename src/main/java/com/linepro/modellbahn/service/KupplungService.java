package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.model.KupplungModel;
import com.linepro.modellbahn.repository.KupplungRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * KupplungService. CRUD service for Kupplung
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class KupplungService extends AbstractNamedItemService<KupplungModel,Kupplung> {

    @Autowired
    public KupplungService(KupplungRepository persister) {
        super(persister);
    }
}
