package com.linepro.modellbahn.service.impl;

/**
 * HerstellerService. CRUD service for Hersteller
 * 
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.HerstellerMutator;
import com.linepro.modellbahn.converter.model.HerstellerModelMutator;
import com.linepro.modellbahn.entity.Hersteller;
import com.linepro.modellbahn.model.HerstellerModel;
import com.linepro.modellbahn.repository.HerstellerRepository;
import com.linepro.modellbahn.service.ItemService;

@Service(PREFIX + "HerstellerService")
public class HerstellerService extends NamedItemServiceImpl<HerstellerModel,Hersteller> implements ItemService<HerstellerModel> {

    @Autowired
    public HerstellerService(HerstellerRepository repository, HerstellerModelMutator modelMutator, HerstellerMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
