package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Wahrung;
import com.linepro.modellbahn.model.WahrungModel;
import com.linepro.modellbahn.repository.WahrungRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * WahrungService. CRUD service for Wahrung
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class WahrungService extends AbstractNamedItemService<WahrungModel,Wahrung> {

    @Autowired
    public WahrungService(WahrungRepository persister) {
        super(persister);
    }
}
