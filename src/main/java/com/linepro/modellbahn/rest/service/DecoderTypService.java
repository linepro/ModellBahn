package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.rest.util.ApiPaths;
import com.linepro.modellbahn.rest.util.NamedItemService;

/**
 * DecoderTypService.
 * CRUD service for DecoderTyp
 * @author  $Author:$
 * @version $Id:$
 */
@Path(ApiPaths.DECODER_TYP)
public class DecoderTypService extends NamedItemService<DecoderTyp> {

    /**
     * Instantiates a new decoder typ service.
     */
    public DecoderTypService() {
        super(DecoderTyp.class);
    }
}
