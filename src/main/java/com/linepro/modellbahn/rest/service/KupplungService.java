package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * KupplungService.
 * CRUD service for Kupplung
 * @author  $Author:$
 * @version $Id:$
 */
@Path("/kupplung")
public class KupplungService extends NamedItemService<Kupplung> {

    /**
     * Instantiates a new kupplung service.
     */
    public KupplungService() {
        super(Kupplung.class);
    }
}
