package com.linepro.modellbahn.service.impl;

/**
 * MassstabService. CRUD service for Massstab
 * 
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.MassstabMutator;
import com.linepro.modellbahn.converter.model.MassstabModelMutator;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;
import com.linepro.modellbahn.repository.MassstabRepository;

@Service(PREFIX + "MassstabService")
public class MassstabService extends NamedItemServiceImpl<MassstabModel,Massstab> {

    @Autowired
    public MassstabService(MassstabRepository repository, MassstabModelMutator modelMutator, MassstabMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
