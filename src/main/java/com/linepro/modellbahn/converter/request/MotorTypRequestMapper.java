package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.request.MotorTypRequest;

@Component(PREFIX + "MotorTypRequestMapper")
public class MotorTypRequestMapper extends MapperImpl<MotorTypRequest, MotorTyp> {

    public MotorTypRequestMapper() {
        super(() -> new MotorTyp(), new NamedRequestTranscriber<MotorTypRequest, MotorTyp>());
    }
}
