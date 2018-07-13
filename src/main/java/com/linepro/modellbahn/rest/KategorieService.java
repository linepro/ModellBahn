package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Kategorie;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/kategorien")
public class KategorieService extends ItemService<Kategorie> {

    public KategorieService() {
        super(Kategorie.class);
    }
}
