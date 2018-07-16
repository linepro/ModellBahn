package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.MotorTyp;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/motorTyp")
public class MotorTypService extends NamedItemService<MotorTyp> {

    public MotorTypService() {
        super(MotorTyp.class);
    }
}
