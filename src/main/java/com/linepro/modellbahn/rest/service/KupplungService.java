package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/kupplung")
public class KupplungService extends NamedItemService<Kupplung> {

    public KupplungService() {
        super(Kupplung.class);
    }
}
