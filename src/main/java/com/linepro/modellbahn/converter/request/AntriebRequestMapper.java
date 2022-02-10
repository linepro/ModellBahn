package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Antrieb;
import com.linepro.modellbahn.request.AntriebRequest;

@Component(PREFIX + "AntriebRequestMapper")
public class AntriebRequestMapper extends MapperImpl<AntriebRequest, Antrieb> {

    @Autowired
    @SuppressWarnings("unchecked")
    public AntriebRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Antrieb(), (Transcriber<AntriebRequest, Antrieb>) transcriber);
    }
}
