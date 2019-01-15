package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.ISonderModell;

public class SonderModellDeserializer extends AbstractItemDeserializer<ISonderModell> {

    private static final long serialVersionUID = -871977401187476757L;

    protected SonderModellDeserializer() {
        this(ISonderModell.class);
    }

    protected SonderModellDeserializer(Class<ISonderModell> vc) {
        super(vc);
    }
}
