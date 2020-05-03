package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.model.GattungModel;
import com.linepro.modellbahn.repository.GattungRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * GattungService. CRUD service for Gattung
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class GattungService extends AbstractNamedItemService<GattungModel,Gattung> {

    @Autowired
    public GattungService(GattungRepository persister) {
        super(persister);
    }
}
