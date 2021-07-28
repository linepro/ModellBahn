package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.converter.request.transcriber.NamedRequestTranscriber;
import com.linepro.modellbahn.entity.Sondermodell;
import com.linepro.modellbahn.request.SondermodellRequest;

@Component(PREFIX + "SondermodellRequestMapper")
public class SondermodellRequestMapper extends MapperImpl<SondermodellRequest, Sondermodell> {

    public SondermodellRequestMapper() {
        super(() -> new Sondermodell(), new NamedRequestTranscriber<SondermodellRequest, Sondermodell>());
    }

}
