package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.MotorTyp;
import com.linepro.modellbahn.request.MotorTypRequest;

@Component(PREFIX + "MotorTypRequestMapper")
public class MotorTypRequestMapper extends MapperImpl<MotorTypRequest, MotorTyp> {

    @Autowired
    @SuppressWarnings("unchecked")
    public MotorTypRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new MotorTyp(), (Transcriber<MotorTypRequest, MotorTyp>) transcriber);
    }
}
