package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IMotorTyp;

public class MotorTypDeserializer extends AbstractItemDeserializer<IMotorTyp> {

    private static final long serialVersionUID = -871977401187476757L;

    protected MotorTypDeserializer() {
        this(IMotorTyp.class);
    }

    protected MotorTypDeserializer(Class<IMotorTyp> vc) {
        super(vc);
    }
}
