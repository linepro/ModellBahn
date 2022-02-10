package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.request.SteuerungRequest;

@Component(PREFIX + "SteuerungRequestMapper")
public class SteuerungRequestMapper extends MapperImpl<SteuerungRequest, Steuerung> {

    @Autowired
    @SuppressWarnings("unchecked")
    public SteuerungRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Steuerung(), (Transcriber<SteuerungRequest, Steuerung>) transcriber);
    }

}
