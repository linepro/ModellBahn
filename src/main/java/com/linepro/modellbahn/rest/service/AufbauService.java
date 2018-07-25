package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * AufbauService.
 * CRUD service for Aufbau
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.AUFBAU)
public class AufbauService extends NamedItemService<Aufbau> {

    /**
     * Instantiates a new aufbau service.
     */
    public AufbauService() {
        super(Aufbau.class);
    }
}
