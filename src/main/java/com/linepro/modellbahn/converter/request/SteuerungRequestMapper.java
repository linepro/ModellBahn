package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Steuerung;
import com.linepro.modellbahn.request.SteuerungRequest;

@Component(PREFIX + "SteuerungRequestMapper")
public class SteuerungRequestMapper extends MapperImpl<SteuerungRequest, Steuerung> {

    public SteuerungRequestMapper() {
        super(() -> new Steuerung(), new NamedRequestTranscriber<SteuerungRequest, Steuerung>());
    }

}
