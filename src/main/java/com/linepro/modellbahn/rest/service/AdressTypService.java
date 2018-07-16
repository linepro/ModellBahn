package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.AdressTyp;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/adressTyp")
public class AdressTypService extends NamedItemService<AdressTyp> {

    public AdressTypService() {
        super(AdressTyp.class);
    }
}
