package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/antrieb")
public class AntriebService extends NamedItemService<Antrieb> {

    public AntriebService() {
        super(Antrieb.class);
    }
}
