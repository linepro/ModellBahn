package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Massstab;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/massstab")
public class MassstabService extends ItemService<Massstab> {

    public MassstabService() {
        super(Massstab.class);
    }
}
