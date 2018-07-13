package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Spurweite;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/spurweite")
public class SpurweiteService extends ItemService<Spurweite> {

    public SpurweiteService() {
        super(Spurweite.class);
    }
}
