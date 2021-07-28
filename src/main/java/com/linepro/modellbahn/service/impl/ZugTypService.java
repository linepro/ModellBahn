package com.linepro.modellbahn.service.impl;

/**
 * ZugTypService. CRUD service for ZugTyp
 * @author $Author:$
 * @version $Id:$
 */

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.ZugTypMapper;
import com.linepro.modellbahn.converter.request.ZugTypRequestMapper;
import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.model.ZugTypModel;
import com.linepro.modellbahn.repository.ZugTypRepository;
import com.linepro.modellbahn.request.ZugTypRequest;

@Service(PREFIX + "ZugTypService")
public class ZugTypService extends NamedItemServiceImpl<ZugTypModel, ZugTypRequest, ZugTyp> {

    @Autowired
    public ZugTypService(ZugTypRepository repository, ZugTypRequestMapper requestMapper, ZugTypMapper entityMapper) {
        super(repository, requestMapper, entityMapper);
    }
}
