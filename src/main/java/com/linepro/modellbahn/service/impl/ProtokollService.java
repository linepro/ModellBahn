package com.linepro.modellbahn.service.impl;

/**
 * ProtokollService. CRUD service for Protokoll
 * 
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.ProtokollMutator;
import com.linepro.modellbahn.converter.model.ProtokollModelMutator;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.service.ItemService;

@Service(PREFIX + "ProtokollService")
public class ProtokollService extends NamedItemServiceImpl<ProtokollModel,Protokoll> implements ItemService<ProtokollModel> {

    @Autowired
    public ProtokollService(ProtokollRepository repository, ProtokollModelMutator modelMutator, ProtokollMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
