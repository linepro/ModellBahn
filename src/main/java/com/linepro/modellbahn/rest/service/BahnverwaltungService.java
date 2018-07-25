package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Bahnverwaltung;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * BahnverwaltungService.
 * CRUD service for Bahnverwaltung
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.BAHNVERWALTUNG)
public class BahnverwaltungService extends NamedItemService<Bahnverwaltung> {

    /**
     * Instantiates a new bahnverwaltung service.
     */
    public BahnverwaltungService() {
        super(Bahnverwaltung.class);
    }
}
