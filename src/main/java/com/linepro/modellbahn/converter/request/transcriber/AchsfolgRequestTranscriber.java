package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Achsfolg;
import com.linepro.modellbahn.request.AchsfolgRequest;
import com.linepro.modellbahn.service.impl.AchsfolgNameGenerator;

@Component(PREFIX + "AchsfolgRequestTranscriber")
public class AchsfolgRequestTranscriber extends NamedRequestTranscriber<AchsfolgRequest, Achsfolg> {

    public AchsfolgRequestTranscriber(AchsfolgNameGenerator generator) {
        super(generator);
    }
}
