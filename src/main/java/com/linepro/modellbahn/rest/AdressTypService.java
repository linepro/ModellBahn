package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.AdressTyp;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/adressTyp")
public class AdressTypService extends ItemService<AdressTyp> {

    public AdressTypService() {
        super(AdressTyp.class);
    }
}
