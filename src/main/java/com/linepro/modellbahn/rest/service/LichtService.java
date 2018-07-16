package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/licht")
public class LichtService extends NamedItemService<Licht> {

    public LichtService() {
        super(Licht.class);
    }
}
