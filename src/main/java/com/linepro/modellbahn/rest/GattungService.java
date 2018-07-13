package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/gattungen")
public class GattungService extends ItemService<Gattung> {

    public GattungService() {
        super(Gattung.class);
    }
}
