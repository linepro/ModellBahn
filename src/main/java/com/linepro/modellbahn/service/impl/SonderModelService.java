package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.SonderModelMutator;
import com.linepro.modellbahn.converter.model.SonderModelModelMutator;
import com.linepro.modellbahn.entity.SonderModel;
import com.linepro.modellbahn.model.SonderModelModel;
import com.linepro.modellbahn.repository.SonderModelRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * SonderModelService. CRUD service for SonderModel
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class SonderModelService extends NamedItemServiceImpl<SonderModelModel,SonderModel> implements ItemService<SonderModelModel> {

    @Autowired
    public SonderModelService(SonderModelRepository repository, SonderModelModelMutator modelMutator, SonderModelMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
