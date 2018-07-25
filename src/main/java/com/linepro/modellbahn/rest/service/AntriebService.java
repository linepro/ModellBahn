package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Antrieb;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * AntriebService.
 * CRUD service for Antrieb
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.ANTRIEB)
public class AntriebService extends NamedItemService<Antrieb> {

    /**
     * Instantiates a new antrieb service.
     */
    public AntriebService() {
        super(Antrieb.class);
    }
}
