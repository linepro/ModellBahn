package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/steuerung")
public class SteuerungService extends ItemService<Steuerung> {

    public SteuerungService() {
        super(Steuerung.class);
    }
}
