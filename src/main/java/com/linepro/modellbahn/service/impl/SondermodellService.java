package com.linepro.modellbahn.service.impl;

/**
 * SondermodellService. CRUD service for Sondermodell
 * 
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.SondermodellMutator;
import com.linepro.modellbahn.converter.request.SondermodellRequestMapper;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.model.SondermodellModel;
import com.linepro.modellbahn.repository.SondermodellRepository;
import com.linepro.modellbahn.request.SondermodellRequest;

@Service(PREFIX + "SondermodellService")
public class SondermodellService extends NamedItemServiceImpl<SondermodellModel, SondermodellRequest, Sondermodell> {

    @Autowired
    public SondermodellService(SondermodellRepository repository, SondermodellRequestMapper requestMapper, SondermodellMutator entityMapper) {
        super(repository, requestMapper, entityMapper);
    }
}
