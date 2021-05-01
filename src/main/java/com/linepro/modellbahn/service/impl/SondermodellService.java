package com.linepro.modellbahn.service.impl;

/**
 * SondermodellService. CRUD service for Sondermodell
 * 
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.SondermodellMutator;
import com.linepro.modellbahn.converter.model.SondermodellModelMutator;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.model.SondermodellModel;
import com.linepro.modellbahn.repository.SondermodellRepository;

@Service(PREFIX + "SondermodellService")
public class SondermodellService extends NamedItemServiceImpl<SondermodellModel,Sondermodell> {

    @Autowired
    public SondermodellService(SondermodellRepository repository, SondermodellModelMutator modelMutator, SondermodellMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
