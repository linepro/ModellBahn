package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/gattungen")
public class GattungService extends NamedItemService<Gattung> {

    public GattungService() {
        super(Gattung.class);
    }
}
