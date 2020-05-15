package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.ZugTypMutator;
import com.linepro.modellbahn.converter.model.ZugTypModelMutator;
import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.model.ZugTypModel;
import com.linepro.modellbahn.repository.ZugTypRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * ZugTypService. CRUD service for ZugTyp
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class ZugTypService extends NamedItemServiceImpl<ZugTypModel,ZugTyp> implements ItemService<ZugTypModel> {

    @Autowired
    public ZugTypService(ZugTypRepository repository, ZugTypModelMutator modelMutator, ZugTypMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
