package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.DecoderTypCv;
import com.linepro.modellbahn.model.DecoderTypCvModel;
import com.linepro.modellbahn.repository.DecoderTypCVRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class DecoderTypCvService extends AbstractItemService<DecoderTypCvModel,DecoderTypCv> {

    private final DecoderTypCVRepository persister;

    @Autowired
    public DecoderTypCvService(DecoderTypCVRepository persister) {
        super(persister);
        
        this.persister = persister;
    }

    @Override
    protected Optional<DecoderTypCv> findByModel(DecoderTypCvModel model) {
        return persister.findByCv(model.getDecoderTyp().getHersteller().getName(), model.getDecoderTyp().getBestellNr(), model.getCv());
    }
}
