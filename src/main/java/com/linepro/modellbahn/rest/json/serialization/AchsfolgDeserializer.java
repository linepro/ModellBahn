package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IAchsfolg;

public class AchsfolgDeserializer extends AbstractItemDeserializer<IAchsfolg> {

    private static final long serialVersionUID = -871977401187476757L;

    protected AchsfolgDeserializer() {
        super(IAchsfolg.class);
    }

    protected AchsfolgDeserializer(Class<IAchsfolg> vc) {
        super(vc);
    }
}