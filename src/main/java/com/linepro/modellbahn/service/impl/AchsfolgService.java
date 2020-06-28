package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.AchsfolgMutator;
import com.linepro.modellbahn.converter.model.AchsfolgModelMutator;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service("AchsfolgService")
public class AchsfolgService extends NamedItemServiceImpl<AchsfolgModel,Achsfolg> {

    @Autowired
    public AchsfolgService(AchsfolgRepository repository, AchsfolgModelMutator modelMutator, AchsfolgMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
