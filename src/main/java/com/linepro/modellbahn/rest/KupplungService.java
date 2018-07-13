package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/kupplung")
public class KupplungService extends ItemService<Kupplung> {

    public KupplungService() {
        super(Kupplung.class);
    }
}
