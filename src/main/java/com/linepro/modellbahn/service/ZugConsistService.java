package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;
import com.linepro.modellbahn.repository.ZugConsistRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class ZugConsistService extends AbstractItemService<ZugConsistModel,ZugConsist> {

    @Autowired
    public ZugConsistService(ZugConsistRepository persister) {
        super(persister);
    }

    @Override
    protected Optional<ZugConsist> findByModel(ZugConsistModel model) {
        return null;
    }
}
