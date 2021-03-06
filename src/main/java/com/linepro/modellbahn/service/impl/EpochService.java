package com.linepro.modellbahn.service.impl;

/**
 * EpochService. CRUD service for Epoch
 * 
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.EpochMutator;
import com.linepro.modellbahn.converter.model.EpochModelMutator;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;
import com.linepro.modellbahn.repository.EpochRepository;
import com.linepro.modellbahn.service.ItemService;

@Service(PREFIX + "EpochService")
public class EpochService extends NamedItemServiceImpl<EpochModel,Epoch> implements ItemService<EpochModel> {

    @Autowired
    public EpochService(EpochRepository repository, EpochModelMutator modelMutator, EpochMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
