package com.linepro.modellbahn.rest;

import javax.ws.rs.Path;

import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.rest.util.ItemService;

@Path("/decoderTyp")
public class DecoderTypService extends ItemService<DecoderTyp> {

    public DecoderTypService() {
        super(DecoderTyp.class);
    }
}
