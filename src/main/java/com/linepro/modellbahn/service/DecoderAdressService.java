package com.linepro.modellbahn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.DecoderAdress;
import com.linepro.modellbahn.model.DecoderAdressModel;
import com.linepro.modellbahn.repository.DecoderAdressRepository;
import com.linepro.modellbahn.service.base.AbstractItemService;

/**
 * AchsfolgService. CRUD service for Achsfolg
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class DecoderAdressService extends AbstractItemService<DecoderAdressModel,DecoderAdress> {

    @Autowired
    public DecoderAdressService(DecoderAdressRepository persister) {
        super(persister);
    }

    @Override
    protected Optional<DecoderAdress> findByModel(DecoderAdressModel model) {
        return null;
    }
}
