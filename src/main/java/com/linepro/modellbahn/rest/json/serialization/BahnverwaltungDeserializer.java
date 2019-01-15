package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IBahnverwaltung;

public class BahnverwaltungDeserializer extends AbstractItemDeserializer<IBahnverwaltung> {

    private static final long serialVersionUID = -871977401187476757L;

    protected BahnverwaltungDeserializer() {
        this(IBahnverwaltung.class);
    }

    protected BahnverwaltungDeserializer(Class<IBahnverwaltung> vc) {
        super(vc);
    }
}
