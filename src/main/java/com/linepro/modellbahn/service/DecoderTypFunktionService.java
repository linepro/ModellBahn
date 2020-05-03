package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.DecoderTypFunktion;
import com.linepro.modellbahn.model.DecoderTypFunktionModel;
import com.linepro.modellbahn.repository.DecoderTypFunktionRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class DecoderTypFunktionService extends AbstractItemService<DecoderTypFunktionModel,DecoderTypFunktion> {

    private DecoderTypFunktionRepository persister;

    @Autowired
    public DecoderTypFunktionService(DecoderTypFunktionRepository persister) {
        super(persister);
        
        this.persister = persister;
    }

    @Override
    protected Optional<DecoderTypFunktion> findByModel(DecoderTypFunktionModel model) {
        return persister.findByFunktion(model.getDecoderTyp().getHersteller().getName(), model.getDecoderTyp().getBestellNr(), model.getReihe(), model.getFunktion());
    }
}
