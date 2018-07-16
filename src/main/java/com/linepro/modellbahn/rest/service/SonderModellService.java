package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.SonderModell;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/sonderModell")
public class SonderModellService extends NamedItemService<SonderModell> {

    public SonderModellService() {
        super(SonderModell.class);
    }
}
