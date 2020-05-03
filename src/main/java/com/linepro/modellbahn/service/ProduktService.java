package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Produkt;
import com.linepro.modellbahn.model.ProduktModel;
import com.linepro.modellbahn.model.ProduktTeilModel;
import com.linepro.modellbahn.repository.ProduktRepository;
import com.linepro.modellbahn.repository.ProduktTeilRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * ProduktService.
 * CRUD service for Produkt
 * @author  $Author:$
 * @version $Id:$
 */

@Service

public class ProduktService extends AbstractItemService<ProduktModel,Produkt> {

    private final ProduktRepository persister;
    
    @Autowired
    public ProduktService(ProduktRepository persister, ProduktTeilRepository teilPersister) {
        super(persister);

        this.persister = persister;
    }

    @Override
    protected Optional<Produkt> findByModel(ProduktModel model) {
        return persister.findByBestellNr(model.getHersteller().getName(), model.getBestellNr());
    }

    public ProduktTeilModel addTeil(ProduktTeilModel teil) {
        return new ProduktTeilModel();
    }

    public Optional<ProduktTeilModel> updateTeil(ProduktTeilModel teil) {
        return Optional.empty();
    }

    public boolean deleteTeil(ProduktTeilModel teil) {
        return false;
    }
}
