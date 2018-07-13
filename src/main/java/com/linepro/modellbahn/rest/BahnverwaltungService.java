package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/bahnverwaltung")
public class BahnverwaltungService extends ItemService<Bahnverwaltung> {

    public BahnverwaltungService() {
        super(Bahnverwaltung.class);
    }
}
