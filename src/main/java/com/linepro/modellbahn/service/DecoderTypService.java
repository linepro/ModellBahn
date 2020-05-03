package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.DecoderTyp;
import com.linepro.modellbahn.model.DecoderTypModel;
import com.linepro.modellbahn.repository.DecoderTypRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class DecoderTypService extends AbstractItemService<DecoderTypModel,DecoderTyp> {

    @Autowired
    public DecoderTypService(DecoderTypRepository persister) {
        super(persister);
    }

    @Override
    protected Optional<DecoderTyp> findByModel(DecoderTypModel model) {
        return null;
    }
}
