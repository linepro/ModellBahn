package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Epoch;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/epochen")
public class EpochService extends ItemService<Epoch> {

    public EpochService() {
        super(Epoch.class);
    }
}
