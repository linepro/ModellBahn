package com.linepro.modellbahn.service.impl;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.AchsfolgMutator;
import com.linepro.modellbahn.converter.model.AchsfolgModelMutator;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;

@Service(PREFIX + "AchsfolgService")
public class AchsfolgService extends NamedItemServiceImpl<AchsfolgModel,Achsfolg> {

    @Autowired
    public AchsfolgService(AchsfolgRepository repository, AchsfolgModelMutator modelMutator, AchsfolgMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
