package com.linepro.modellbahn.service.impl;

/**
 * AntriebService. CRUD service for Antrieb
 * 
 * @author $Author:$
 * @version $Id:$
 */
import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.AntriebMutator;
import com.linepro.modellbahn.converter.model.AntriebModelMutator;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;
import com.linepro.modellbahn.repository.AntriebRepository;

@Service(PREFIX + "AntriebService")
public class AntriebService extends NamedItemServiceImpl<AntriebModel,Antrieb> {

    @Autowired
    public AntriebService(AntriebRepository repository, AntriebModelMutator modelMutator, AntriebMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
