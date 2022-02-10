package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Kupplung;
import com.linepro.modellbahn.request.KupplungRequest;

@Component(PREFIX + "KupplungRequestMapper")
public class KupplungRequestMapper extends MapperImpl<KupplungRequest, Kupplung> {

    @Autowired
    @SuppressWarnings("unchecked")
    public KupplungRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Kupplung(), (Transcriber<KupplungRequest, Kupplung>) transcriber);
    }

}
