package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IWahrung;

public class WahrungDeserializer extends AbstractItemDeserializer<IWahrung> {

    private static final long serialVersionUID = -871977401187476757L;

    protected WahrungDeserializer() {
        this(IWahrung.class);
    }

    protected WahrungDeserializer(Class<IWahrung> vc) {
        super(vc);
    }
}
