package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.request.AntriebRequest;

@Component(PREFIX + "AntriebRequestMapper")
public class AntriebRequestMapper extends MapperImpl<AntriebRequest, Antrieb> {

    public AntriebRequestMapper() {
        super(() -> new Antrieb(), new NamedRequestTranscriber<AntriebRequest, Antrieb>());
    }
}
