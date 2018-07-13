package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/achsfolg")
public class AchsfolgService extends ItemService<Achsfolg> {

    public AchsfolgService() {
        super(Achsfolg.class);
    }
}
