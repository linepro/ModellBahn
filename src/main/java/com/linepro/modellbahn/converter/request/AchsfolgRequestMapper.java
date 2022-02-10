package com.linepro.modellbahn.converter.request;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.converter.impl.MapperImpl;
import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.request.AchsfolgRequest;

@Component(PREFIX + "AchsfolgRequestMapper")
public class AchsfolgRequestMapper extends MapperImpl<AchsfolgRequest, Achsfolg> {

    @Autowired
    @SuppressWarnings("unchecked")
    public AchsfolgRequestMapper(@Qualifier(PREFIX + "NamedRequestTranscriber") Transcriber<?,?> transcriber) {
        super(() -> new Achsfolg(), (Transcriber<AchsfolgRequest, Achsfolg>) transcriber);
    }
}
