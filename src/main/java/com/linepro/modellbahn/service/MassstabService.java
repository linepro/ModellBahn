package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;
import com.linepro.modellbahn.repository.MassstabRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * MassstabService. CRUD service for Massstab
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class MassstabService extends AbstractNamedItemService<MassstabModel,Massstab> {

    @Autowired
    public MassstabService(MassstabRepository persister) {
        super(persister);
    }
}
