package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.Epoch;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * EpochService.
 * CRUD service for Epoch
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.EPOCH)
public class EpochService extends NamedItemService<Epoch> {

    /**
     * Instantiates a new epoch service.
     */
    public EpochService() {
        super(Epoch.class);
    }
}
