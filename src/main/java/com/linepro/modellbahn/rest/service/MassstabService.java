package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Massstab;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/massstab")
public class MassstabService extends NamedItemService<Massstab> {

    public MassstabService() {
        super(Massstab.class);
    }
}
