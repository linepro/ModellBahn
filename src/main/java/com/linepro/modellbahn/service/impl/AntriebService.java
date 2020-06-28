package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.AntriebMutator;
import com.linepro.modellbahn.converter.model.AntriebModelMutator;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.model.AntriebModel;
import com.linepro.modellbahn.repository.AntriebRepository;

/**
 * AntriebService. CRUD service for Antrieb
 * 
 * @author $Author:$
 * @version $Id:$
 */
@Service("AntriebService")
public class AntriebService extends NamedItemServiceImpl<AntriebModel,Antrieb> {

    @Autowired
    public AntriebService(AntriebRepository repository, AntriebModelMutator modelMutator, AntriebMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
