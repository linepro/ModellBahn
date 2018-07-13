package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/protokoll")
public class ProtokollService extends ItemService<Protokoll> {

    public ProtokollService() {
        super(Protokoll.class);
    }
}
