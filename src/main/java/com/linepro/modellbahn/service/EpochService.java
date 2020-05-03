package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;
import com.linepro.modellbahn.repository.EpochRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * EpochService. CRUD service for Epoch
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class EpochService extends AbstractNamedItemService<EpochModel,Epoch> {

    @Autowired
    public EpochService(EpochRepository persister) {
        super(persister);
    }
}
