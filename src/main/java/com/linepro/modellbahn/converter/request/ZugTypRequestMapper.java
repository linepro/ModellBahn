package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.request.ZugTypRequest;

@Component(PREFIX + "ZugTypRequestMapper")
public class ZugTypRequestMapper extends MapperImpl<ZugTypRequest, ZugTyp> {

    public ZugTypRequestMapper() {
        super(() -> new ZugTyp(), new NamedRequestTranscriber<>());
    }
}
