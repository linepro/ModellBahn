package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Gattung;
import com.linepro.modellbahn.request.GattungRequest;

@Component(PREFIX + "GattungRequestMapper")
public class GattungRequestMapper extends MapperImpl<GattungRequest, Gattung> {

    @Autowired
    @SuppressWarnings("unchecked")
    public GattungRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Gattung(), (Transcriber<GattungRequest, Gattung>) transcriber);
    }
}
