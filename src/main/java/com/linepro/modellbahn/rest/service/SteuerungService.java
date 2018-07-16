package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/steuerung")
public class SteuerungService extends NamedItemService<Steuerung> {

    public SteuerungService() {
        super(Steuerung.class);
    }
}
