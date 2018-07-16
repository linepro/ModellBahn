package com.linepro.modellbahn.rest.service;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.rest.util.NamedItemService;

@Path("/decoderTyp")
public class DecoderTypService extends NamedItemService<DecoderTyp> {

    public DecoderTypService() {
        super(DecoderTyp.class);
    }
}
