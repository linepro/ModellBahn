package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.request.ProtokollRequest;

@Component(PREFIX + "ProtokollRequestMapper")
public class ProtokollRequestMapper extends MapperImpl<ProtokollRequest, Protokoll> {

    public ProtokollRequestMapper() {
        super(() -> new Protokoll(), new NamedRequestTranscriber<ProtokollRequest, Protokoll>());
    }

}
