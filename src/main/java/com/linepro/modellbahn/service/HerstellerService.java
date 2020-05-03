package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.repository.HerstellerRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * HerstellerService. CRUD service for Hersteller
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class HerstellerService extends AbstractNamedItemService<HerstellerModel,Hersteller> {

    @Autowired
    public HerstellerService(HerstellerRepository persister) {
        super(persister);
    }
}
