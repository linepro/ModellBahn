package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IGattung;

public class GattungDeserializer extends AbstractItemDeserializer<IGattung> {

    private static final long serialVersionUID = -871977401187476757L;

    protected GattungDeserializer() {
        this(IGattung.class);
    }

    protected GattungDeserializer(Class<IGattung> vc) {
        super(vc);
    }
}
