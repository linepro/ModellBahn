package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Decoder;
import com.linepro.modellbahn.model.DecoderModel;
import com.linepro.modellbahn.repository.DecoderRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * DecoderService. CRUD service for Decoder
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class DecoderService extends AbstractItemService<DecoderModel,Decoder> {

    private final DecoderRepository persister;

    @Autowired
    public DecoderService(DecoderRepository persister) {
        super(persister);
        
        this.persister = persister;
    }

    @Override
    protected Optional<Decoder> findByModel(DecoderModel model) {
        return persister.findByDecoderId(model.getDecoderId());
    }

    public DecoderModel addDecoder(String herstellerStr, String bestellNr) {
        // TODO Auto-generated method stub
        return null;
    }
}
