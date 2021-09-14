package com.linepro.modellbahn.service.impl;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.AchsfolgMapper;
import com.linepro.modellbahn.converter.request.AchsfolgRequestMapper;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.model.AchsfolgModel;
import com.linepro.modellbahn.repository.AchsfolgRepository;
import com.linepro.modellbahn.repository.lookup.AchsfolgLookup;
import com.linepro.modellbahn.request.AchsfolgRequest;

@Service(PREFIX + "AchsfolgService")
public class AchsfolgService extends NamedItemServiceImpl<AchsfolgModel, AchsfolgRequest, Achsfolg> {

    @Autowired
    public AchsfolgService(AchsfolgRepository repository, AchsfolgRequestMapper requestMapper, AchsfolgMapper entityMapper, AchsfolgLookup lookup) {
        super(repository, requestMapper, entityMapper, lookup);
    }
}
