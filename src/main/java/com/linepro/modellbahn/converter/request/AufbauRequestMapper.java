package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Aufbau;
import com.linepro.modellbahn.request.AufbauRequest;

@Component(PREFIX + "AufbauRequestMapper")
public class AufbauRequestMapper extends MapperImpl<AufbauRequest, Aufbau> {

    public AufbauRequestMapper() {
        super(() -> new Aufbau(), new NamedRequestTranscriber<AufbauRequest, Aufbau>());
    }
}
