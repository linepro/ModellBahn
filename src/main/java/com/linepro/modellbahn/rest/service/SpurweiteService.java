package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Spurweite;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/spurweite")
public class SpurweiteService extends NamedItemService<Spurweite> {

    public SpurweiteService() {
        super(Spurweite.class);
    }
}
