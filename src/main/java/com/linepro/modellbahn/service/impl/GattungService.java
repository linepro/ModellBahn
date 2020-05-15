package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.GattungMutator;
import com.linepro.modellbahn.converter.model.GattungModelMutator;
import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.model.GattungModel;
import com.linepro.modellbahn.repository.GattungRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * GattungService. CRUD service for Gattung
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class GattungService extends NamedItemServiceImpl<GattungModel,Gattung> implements ItemService<GattungModel> {

    @Autowired
    public GattungService(GattungRepository repository, GattungModelMutator modelMutator, GattungMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
