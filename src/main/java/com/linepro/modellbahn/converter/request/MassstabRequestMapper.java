package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.request.MassstabRequest;

@Component(PREFIX + "MassstabRequestMapper")
public class MassstabRequestMapper extends MapperImpl<MassstabRequest, Massstab> {

    @Autowired
    @SuppressWarnings("unchecked")
    public MassstabRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Massstab(), (Transcriber<MassstabRequest, Massstab>) transcriber);
    }

}
