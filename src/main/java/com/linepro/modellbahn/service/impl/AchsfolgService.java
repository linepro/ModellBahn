package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class AchsfolgService extends NamedItemServiceImpl<AchsfolgModel,Achsfolg> {

    @Autowired
    public AchsfolgService(AchsfolgRepository repository) {
        super(repository, () -> new AchsfolgModel(), () -> new Achsfolg());
    }
}
