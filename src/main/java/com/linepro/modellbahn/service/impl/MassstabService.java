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
import com.linepro.modellbahn.converter.request.MassstabRequestMapper;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;
import com.linepro.modellbahn.repository.MassstabRepository;
import com.linepro.modellbahn.request.MassstabRequest;

@Service(PREFIX + "MassstabService")
public class MassstabService extends NamedItemServiceImpl<MassstabModel, MassstabRequest, Massstab> {

    @Autowired
    public MassstabService(MassstabRepository repository, MassstabRequestMapper requestMapper, MassstabMutator entityMapper) {
        super(repository, requestMapper, entityMapper);
    }
}
