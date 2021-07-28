package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.request.LichtRequest;

@Component(PREFIX + "LichtRequestMapper")
public class LichtRequestMapper extends MapperImpl<LichtRequest, Licht> {

    public LichtRequestMapper() {
        super(() -> new Licht(), new NamedRequestTranscriber<LichtRequest, Licht>());
    }

}
