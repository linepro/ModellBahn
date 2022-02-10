package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Protokoll;
import com.linepro.modellbahn.request.ProtokollRequest;

@Component(PREFIX + "ProtokollRequestMapper")
public class ProtokollRequestMapper extends MapperImpl<ProtokollRequest, Protokoll> {

    @Autowired
    @SuppressWarnings("unchecked")
    public ProtokollRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Protokoll(), (Transcriber<ProtokollRequest, Protokoll>) transcriber);
    }

}
