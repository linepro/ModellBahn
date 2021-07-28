package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Massstab;
import com.linepro.modellbahn.request.MassstabRequest;

@Component(PREFIX + "MassstabRequestMapper")
public class MassstabRequestMapper extends MapperImpl<MassstabRequest, Massstab> {

    public MassstabRequestMapper() {
        super(() -> new Massstab(), new NamedRequestTranscriber<MassstabRequest, Massstab>());
    }

}
