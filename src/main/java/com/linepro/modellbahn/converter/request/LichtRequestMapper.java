package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Licht;
import com.linepro.modellbahn.request.LichtRequest;

@Component(PREFIX + "LichtRequestMapper")
public class LichtRequestMapper extends MapperImpl<LichtRequest, Licht> {

    @Autowired
    @SuppressWarnings("unchecked")
    public LichtRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Licht(), (Transcriber<LichtRequest, Licht>) transcriber);
    }

}
