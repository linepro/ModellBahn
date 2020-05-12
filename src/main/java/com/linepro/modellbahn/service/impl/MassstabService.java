package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.model.MassstabModel;
import com.linepro.modellbahn.repository.MassstabRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * MassstabService. CRUD service for Massstab
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class MassstabService extends NamedItemServiceImpl<MassstabModel,Massstab> implements ItemService<MassstabModel> {

    @Autowired
    public MassstabService(MassstabRepository repository) {
        super(repository, () -> new MassstabModel(), () -> new Massstab());
    }
}
