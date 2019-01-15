package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.ILicht;

public class LichtDeserializer extends AbstractItemDeserializer<ILicht> {

    private static final long serialVersionUID = -871977401187476757L;

    protected LichtDeserializer() {
        this(ILicht.class);
    }

    protected LichtDeserializer(Class<ILicht> vc) {
        super(vc);
    }
}
