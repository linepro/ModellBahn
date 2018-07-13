package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/aufbau")
public class AufbauService extends ItemService<Aufbau> {

    public AufbauService() {
        super(Aufbau.class);
    }
}
