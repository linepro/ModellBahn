package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.MotorTyp;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/motorTyp")
public class MotorTypService extends ItemService<MotorTyp> {

    public MotorTypService() {
        super(MotorTyp.class);
    }
}
