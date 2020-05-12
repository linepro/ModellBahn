package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.repository.HerstellerRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * HerstellerService. CRUD service for Hersteller
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class HerstellerService extends NamedItemServiceImpl<HerstellerModel,Hersteller> implements ItemService<HerstellerModel> {

    @Autowired
    public HerstellerService(HerstellerRepository repository) {
        super(repository, () -> new HerstellerModel(), () -> new Hersteller());
    }
}
