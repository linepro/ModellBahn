package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Spurweite;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * SpurweiteService.
 * CRUD service for Spurweite
 * @author  $Author:$
 * @version $Id:$
 */
@Path("/spurweite")
public class SpurweiteService extends NamedItemService<Spurweite> {

    /**
     * Instantiates a new spurweite service.
     */
    public SpurweiteService() {
        super(Spurweite.class);
    }
}
