package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/kategorien")
public class KategorieService extends NamedItemService<Kategorie> {

    public KategorieService() {
        super(Kategorie.class);
    }
}
