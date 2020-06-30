package com.linepro.modellbahn.service.impl;

/**
 * SteuerungService. CRUD service for Steuerung
 * 
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.SteuerungMutator;
import com.linepro.modellbahn.converter.model.SteuerungModelMutator;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.service.ItemService;

@Service(PREFIX + "SteuerungService")
public class SteuerungService extends NamedItemServiceImpl<SteuerungModel,Steuerung> implements ItemService<SteuerungModel> {

    @Autowired
    public SteuerungService(SteuerungRepository repository, SteuerungModelMutator modelMutator, SteuerungMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
