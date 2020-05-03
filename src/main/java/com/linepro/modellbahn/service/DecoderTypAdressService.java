package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.DecoderTypAdress;
import com.linepro.modellbahn.model.DecoderTypAdressModel;
import com.linepro.modellbahn.repository.DecoderTypAdressRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class DecoderTypAdressService extends AbstractItemService<DecoderTypAdressModel,DecoderTypAdress> {

    private final DecoderTypAdressRepository persister;
    
    @Autowired
    public DecoderTypAdressService(DecoderTypAdressRepository persister) {
        super(persister);
        
        this.persister = persister;
    }

    @Override
    protected Optional<DecoderTypAdress> findByModel(DecoderTypAdressModel model) {
        return persister.findByIndex(model.getDecoderTyp().getHersteller().getName(), model.getDecoderTyp().getBestellNr(), model.getIndex());
    }
}
