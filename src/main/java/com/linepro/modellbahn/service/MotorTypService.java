package com.linepro.modellbahn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;
import com.linepro.modellbahn.repository.MotorTypRepository;
import com.linepro.modellbahn.service.base.AbstractNamedItemService;

/**
 * MotorTypService. CRUD service for MotorTyp
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class MotorTypService extends AbstractNamedItemService<MotorTypModel,MotorTyp> {

    @Autowired
    public MotorTypService(MotorTypRepository persister) {
        super(persister);
    }
}
