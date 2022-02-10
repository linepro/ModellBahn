package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.request.AufbauRequest;

@Component(PREFIX + "AufbauRequestMapper")
public class AufbauRequestMapper extends MapperImpl<AufbauRequest, Aufbau> {

    @Autowired
    @SuppressWarnings("unchecked")
    public AufbauRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Aufbau(), (Transcriber<AufbauRequest, Aufbau>) transcriber);
    }
}
