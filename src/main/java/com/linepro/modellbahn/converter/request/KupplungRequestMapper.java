package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.request.KupplungRequest;

@Component(PREFIX + "KupplungRequestMapper")
public class KupplungRequestMapper extends MapperImpl<KupplungRequest, Kupplung> {

    public KupplungRequestMapper() {
        super(() -> new Kupplung(), new NamedRequestTranscriber<KupplungRequest, Kupplung>());
    }

}
