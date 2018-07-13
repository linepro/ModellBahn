package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/antrieb")
public class AntriebService extends ItemService<Antrieb> {

    public AntriebService() {
        super(Antrieb.class);
    }
}
