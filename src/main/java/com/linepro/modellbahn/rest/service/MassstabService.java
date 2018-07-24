package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Massstab;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * MassstabService.
 * CRUD service for Massstab
 * @author  $Author:$
 * @version $Id:$
 */
@Path("/massstab")
public class MassstabService extends NamedItemService<Massstab> {

    /**
     * Instantiates a new massstab service.
     */
    public MassstabService() {
        super(Massstab.class);
    }
}
