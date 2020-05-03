package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ZugRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * ZugService. CRUD service for Zug
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class ZugService extends AbstractNamedItemService<ZugModel,Zug> {

    @Autowired
    public ZugService(ZugRepository persister) {
        super(persister);
    }
}
