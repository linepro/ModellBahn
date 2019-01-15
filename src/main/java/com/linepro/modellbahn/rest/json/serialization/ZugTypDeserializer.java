package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IZugTyp;

public class ZugTypDeserializer extends AbstractItemDeserializer<IZugTyp> {

    private static final long serialVersionUID = -871977401187476757L;

    protected ZugTypDeserializer() {
        this(IZugTyp.class);
    }

    protected ZugTypDeserializer(Class<IZugTyp> vc) {
        super(vc);
    }
}
