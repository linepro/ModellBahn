package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Spurweite;
import com.linepro.modellbahn.request.SpurweiteRequest;

@Component(PREFIX + "SpurweiteRequestMapper")
public class SpurweiteRequestMapper extends MapperImpl<SpurweiteRequest, Spurweite> {

    @Autowired
    @SuppressWarnings("unchecked")
    public SpurweiteRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Spurweite(), (Transcriber<SpurweiteRequest, Spurweite>) transcriber);
    }

}
