package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Licht;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * LichtService.
 * CRUD service for Licht
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.LICHT)
public class LichtService extends NamedItemService<Licht> {

    /**
     * Instantiates a new licht service.
     */
    public LichtService() {
        super(Licht.class);
    }
}
