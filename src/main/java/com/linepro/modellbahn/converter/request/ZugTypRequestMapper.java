package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.request.ZugTypRequest;

@Component(PREFIX + "ZugTypRequestMapper")
public class ZugTypRequestMapper extends MapperImpl<ZugTypRequest, ZugTyp> {

    @Autowired
    @SuppressWarnings("unchecked")
    public ZugTypRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new ZugTyp(), (Transcriber<ZugTypRequest, ZugTyp>) transcriber);
    }
}
