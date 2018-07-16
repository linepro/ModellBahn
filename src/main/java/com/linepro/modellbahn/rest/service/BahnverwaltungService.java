package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/bahnverwaltung")
public class BahnverwaltungService extends NamedItemService<Bahnverwaltung> {

    public BahnverwaltungService() {
        super(Bahnverwaltung.class);
    }
}
