package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.request.GattungRequest;

@Component(PREFIX + "GattungRequestMapper")
public class GattungRequestMapper extends MapperImpl<GattungRequest, Gattung> {

    public GattungRequestMapper() {
        super(() -> new Gattung(), new NamedRequestTranscriber<GattungRequest, Gattung>());
    }

}
