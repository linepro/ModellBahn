package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.model.AufbauModel;
import com.linepro.modellbahn.repository.AufbauRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * AufbauService. CRUD service for Aufbau
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class AufbauService extends AbstractNamedItemService<AufbauModel,Aufbau> {

    @Autowired
    public AufbauService(AufbauRepository persister) {
        super(persister);
    }
}
