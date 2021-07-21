package com.linepro.modellbahn.service.impl;

/**
 * GattungService. CRUD service for Gattung
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.GattungMutator;
import com.linepro.modellbahn.converter.request.GattungRequestMapper;
import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.model.GattungModel;
import com.linepro.modellbahn.repository.GattungRepository;
import com.linepro.modellbahn.request.GattungRequest;

@Service(PREFIX + "GattungService")
public class GattungService extends NamedItemServiceImpl<GattungModel, GattungRequest, Gattung> {

    @Autowired
    public GattungService(GattungRepository repository, GattungRequestMapper requestMapper, GattungMutator entityMapper) {
        super(repository, requestMapper, entityMapper);
    }
}
