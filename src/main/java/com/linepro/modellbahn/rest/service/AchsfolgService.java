package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/achsfolg")
public class AchsfolgService extends NamedItemService<Achsfolg> {

    public AchsfolgService() {
        super(Achsfolg.class);
    }
}
