package com.linepro.modellbahn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.converter.entity.MotorTypMutator;
import com.linepro.modellbahn.converter.model.MotorTypModelMutator;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.model.MotorTypModel;
import com.linepro.modellbahn.repository.MotorTypRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * MotorTypService. CRUD service for MotorTyp
 * 
 * @author $Author:$
 * @version $Id:$
 */

@Service
public class MotorTypService extends NamedItemServiceImpl<MotorTypModel,MotorTyp> implements ItemService<MotorTypModel> {

    @Autowired
    public MotorTypService(MotorTypRepository repository, MotorTypModelMutator modelMutator, MotorTypMutator entityMutator) {
        super(repository, modelMutator, entityMutator);
    }
}
