package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.model.ProtokollModel;
import com.linepro.modellbahn.repository.ProtokollRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * ProtokollService. CRUD service for Protokoll
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class ProtokollService extends AbstractNamedItemService<ProtokollModel,Protokoll> {

    @Autowired
    public ProtokollService(ProtokollRepository persister) {
        super(persister);
    }
}
