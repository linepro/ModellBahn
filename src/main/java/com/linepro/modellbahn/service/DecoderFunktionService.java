package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.DecoderFunktion;
import com.linepro.modellbahn.model.DecoderFunktionModel;
import com.linepro.modellbahn.repository.DecoderFunktionRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class DecoderFunktionService extends AbstractItemService<DecoderFunktionModel,DecoderFunktion> {
    
    @Autowired
    public DecoderFunktionService(DecoderFunktionRepository persister) {
        super(persister);
    }

    @Override
    protected Optional<DecoderFunktion> findByModel(DecoderFunktionModel model) {
        return null;
    }
}
