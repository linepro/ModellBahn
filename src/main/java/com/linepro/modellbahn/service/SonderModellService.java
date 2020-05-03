package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.SonderModell;
import com.linepro.modellbahn.model.SonderModellModel;
import com.linepro.modellbahn.repository.SonderModellRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * SonderModellService. CRUD service for SonderModell
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class SonderModellService extends AbstractNamedItemService<SonderModellModel,SonderModell> {

    @Autowired
    public SonderModellService(SonderModellRepository persister) {
        super(persister);
    }
}
