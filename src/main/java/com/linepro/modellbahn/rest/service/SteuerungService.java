package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Steuerung;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * SteuerungService.
 * CRUD service for Steuerung
 * @author  $Author:$
 * @version $Id:$
 */
@Path("/steuerung")
public class SteuerungService extends NamedItemService<Steuerung> {

    /**
     * Instantiates a new steuerung service.
     */
    public SteuerungService() {
        super(Steuerung.class);
    }
}
