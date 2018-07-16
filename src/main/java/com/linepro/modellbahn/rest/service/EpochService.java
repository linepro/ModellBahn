package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Epoch;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/epochen")
public class EpochService extends NamedItemService<Epoch> {

    public EpochService() {
        super(Epoch.class);
    }
}
