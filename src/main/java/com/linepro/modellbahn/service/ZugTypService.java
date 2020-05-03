package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.model.ZugTypModel;
import com.linepro.modellbahn.repository.ZugTypRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * ZugTypService. CRUD service for ZugTyp
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class ZugTypService extends AbstractNamedItemService<ZugTypModel,ZugTyp> {

    @Autowired
    public ZugTypService(ZugTypRepository persister) {
        super(persister);
    }
}
