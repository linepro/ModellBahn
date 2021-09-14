package com.linepro.modellbahn.service.impl;

/**
 * SpurweiteService. CRUD service for Spurweite
 * 
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.SpurweiteMapper;
import com.linepro.modellbahn.converter.request.SpurweiteRequestMapper;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.model.SpurweiteModel;
import com.linepro.modellbahn.repository.SpurweiteRepository;
import com.linepro.modellbahn.repository.lookup.SpurweiteLookup;
import com.linepro.modellbahn.request.SpurweiteRequest;

@Service(PREFIX + "SpurweiteService")
public class SpurweiteService extends NamedItemServiceImpl<SpurweiteModel, SpurweiteRequest, Spurweite> {

    @Autowired
    public SpurweiteService(SpurweiteRepository repository, SpurweiteRequestMapper requestMapper, SpurweiteMapper entityMapper, SpurweiteLookup lookup) {
        super(repository, requestMapper, entityMapper, lookup);
    }
}
