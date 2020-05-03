package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Vorbild;
import com.linepro.modellbahn.model.VorbildModel;
import com.linepro.modellbahn.repository.VorbildRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * VorbildService. CRUD service for Vorbild
 * @author $Author:$
 * @version $Id:$
 */

@Service

public class VorbildService extends AbstractItemService<VorbildModel,Vorbild> {

    private VorbildRepository persister;

    @Autowired
    public VorbildService(VorbildRepository persister) {
        super(persister);

        this.persister = persister;
    }

    @Override
    protected Optional<Vorbild> findByModel(VorbildModel model) {
        return persister.findByGattung(model.getGattung().getName());
    }
}
