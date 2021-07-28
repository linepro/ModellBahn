package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.request.SpurweiteRequest;

@Component(PREFIX + "SpurweiteRequestMapper")
public class SpurweiteRequestMapper extends MapperImpl<SpurweiteRequest, Spurweite> {

    public SpurweiteRequestMapper() {
        super(() -> new Spurweite(), new NamedRequestTranscriber<SpurweiteRequest, Spurweite>());
    }

}
