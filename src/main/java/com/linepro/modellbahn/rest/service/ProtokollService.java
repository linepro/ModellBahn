package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Protokoll;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * ProtokollService.
 * CRUD service for Protokoll
 * @author  $Author:$
 * @version $Id:$
 */
@Path("/protokoll")
public class ProtokollService extends NamedItemService<Protokoll> {

    /**
     * Instantiates a new protokoll service.
     */
    public ProtokollService() {
        super(Protokoll.class);
    }
}
