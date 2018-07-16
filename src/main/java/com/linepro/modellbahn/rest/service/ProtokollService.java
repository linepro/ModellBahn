package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/protokoll")
public class ProtokollService extends NamedItemService<Protokoll> {

    public ProtokollService() {
        super(Protokoll.class);
    }
}
