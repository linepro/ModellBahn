package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.DecoderCv;
import com.linepro.modellbahn.model.DecoderCvModel;
import com.linepro.modellbahn.repository.DecoderCvRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class DecoderCvService extends AbstractItemService<DecoderCvModel,DecoderCv> {

    @Autowired
    public DecoderCvService(DecoderCvRepository persister) {
        super(persister);
    }

    @Override
    protected Optional<DecoderCv> findByModel(DecoderCvModel model) {
        return null;
    }
}
