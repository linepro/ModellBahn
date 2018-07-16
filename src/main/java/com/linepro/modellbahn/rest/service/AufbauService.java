package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/aufbau")
public class AufbauService extends NamedItemService<Aufbau> {

    public AufbauService() {
        super(Aufbau.class);
    }
}
