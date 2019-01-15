package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.ISteuerung;

public class SteuerungDeserializer extends AbstractItemDeserializer<ISteuerung> {

    private static final long serialVersionUID = -871977401187476757L;

    protected SteuerungDeserializer() {
        this(ISteuerung.class);
    }

    protected SteuerungDeserializer(Class<ISteuerung> vc) {
        super(vc);
    }
}
