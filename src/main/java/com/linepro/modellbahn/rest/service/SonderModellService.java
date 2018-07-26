package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.SonderModell;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * SonderModellService.
 * CRUD service for SonderModell
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.SONDERMODEL)
public class SonderModellService extends NamedItemService<SonderModell> {

    /**
     * Instantiates a new sonder modell service.
     */
    public SonderModellService() {
        super(SonderModell.class);
    }
}
