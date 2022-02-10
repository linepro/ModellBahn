package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.request.SondermodellRequest;

@Component(PREFIX + "SondermodellRequestMapper")
public class SondermodellRequestMapper extends MapperImpl<SondermodellRequest, Sondermodell> {

    @Autowired
    @SuppressWarnings("unchecked")
    public SondermodellRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Sondermodell(), (Transcriber<SondermodellRequest, Sondermodell>) transcriber);
    }

}
