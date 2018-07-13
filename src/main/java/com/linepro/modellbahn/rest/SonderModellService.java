package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.SonderModell;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/sonderModell")
public class SonderModellService extends ItemService<SonderModell> {

    public SonderModellService() {
        super(SonderModell.class);
    }
}
