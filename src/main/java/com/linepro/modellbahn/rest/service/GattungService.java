package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Gattung;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * GattungService.
 * CRUD service for Gattung
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.GATTUNG)
public class GattungService extends NamedItemService<Gattung> {

    /**
     * Instantiates a new gattung service.
     */
    public GattungService() {
        super(Gattung.class);
    }
}
