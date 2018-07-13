package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/licht")
public class LichtService extends ItemService<Licht> {

    public LichtService() {
        super(Licht.class);
    }
}
