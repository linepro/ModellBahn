package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Anderung;
import com.linepro.modellbahn.model.AnderungModel;
import com.linepro.modellbahn.repository.AnderungRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class AnderungService extends AbstractItemService<AnderungModel,Anderung> {

    @Autowired
    public AnderungService(AnderungRepository persister) {
        super(persister);
    }

    @Override
    protected Optional<Anderung> findByModel(AnderungModel model) {
        return null;
    }
}
