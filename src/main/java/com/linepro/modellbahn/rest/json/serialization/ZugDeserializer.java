package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IZug;

public class ZugDeserializer extends AbstractItemDeserializer<IZug> {

    private static final long serialVersionUID = -871977401187476757L;

    protected ZugDeserializer() {
        this(IZug.class);
    }

    protected ZugDeserializer(Class<IZug> vc) {
        super(vc);
    }
}
