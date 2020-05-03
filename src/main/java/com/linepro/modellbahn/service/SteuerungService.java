package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * SteuerungService. CRUD service for Steuerung
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class SteuerungService extends AbstractNamedItemService<SteuerungModel,Steuerung> {

    @Autowired
    public SteuerungService(SteuerungRepository persister) {
        super(persister);
    }
}
