package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Achsfolg;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * AchsfolgService.
 * CRUD service for Achsfolg
 * @author  $Author:$
 * @version $Id:$
 */
@Path("/achsfolg")
public class AchsfolgService extends NamedItemService<Achsfolg> {

    /**
     * Instantiates a new achsfolg service.
     */
    public AchsfolgService() {
        super(Achsfolg.class);
    }
}
