package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Bahnverwaltung;
import com.linepro.modellbahn.model.BahnverwaltungModel;
import com.linepro.modellbahn.repository.BahnverwaltungRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * BahnverwaltungService. CRUD service for Bahnverwaltung
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class BahnverwaltungService extends AbstractNamedItemService<BahnverwaltungModel,Bahnverwaltung> {

    @Autowired
    public BahnverwaltungService(BahnverwaltungRepository persister) {
        super(persister);
    }
}
