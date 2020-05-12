package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.model.SteuerungModel;
import com.linepro.modellbahn.repository.SteuerungRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * SteuerungService. CRUD service for Steuerung
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class SteuerungService extends NamedItemServiceImpl<SteuerungModel,Steuerung> implements ItemService<SteuerungModel> {

    @Autowired
    public SteuerungService(SteuerungRepository repository) {
        super(repository, () -> new SteuerungModel(), () -> new Steuerung());
    }
}
