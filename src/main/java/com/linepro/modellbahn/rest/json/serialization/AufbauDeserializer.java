package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IAufbau;

public class AufbauDeserializer extends AbstractItemDeserializer<IAufbau> {

    private static final long serialVersionUID = -871977401187476757L;

    protected AufbauDeserializer() {
        this(IAufbau.class);
    }

    protected AufbauDeserializer(Class<IAufbau> vc) {
        super(vc);
    }
}
