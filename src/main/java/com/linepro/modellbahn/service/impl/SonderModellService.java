package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.SonderModell;
import com.linepro.modellbahn.model.SonderModellModel;
import com.linepro.modellbahn.repository.SonderModellRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * SonderModellService. CRUD service for SonderModell
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class SonderModellService extends NamedItemServiceImpl<SonderModellModel,SonderModell> implements ItemService<SonderModellModel> {

    @Autowired
    public SonderModellService(SonderModellRepository repository) {
        super(repository, () -> new SonderModellModel(), () -> new SonderModell());
    }
}
