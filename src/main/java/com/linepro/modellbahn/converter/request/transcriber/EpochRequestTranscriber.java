package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.request.EpochRequest;
import com.linepro.modellbahn.service.NameGenerator;

@Component(PREFIX + "EpochRequestTranscriber")
public class EpochRequestTranscriber extends NamedRequestTranscriber<EpochRequest, Epoch> {

    public EpochRequestTranscriber(NameGenerator generator) {
        super(generator);
    }

    @Override
    public Epoch apply(EpochRequest source, Epoch destination) {
        destination.setStartYear(source.getStartYear());
        destination.setEndYear(source.getEndYear());

        return super.apply(source, destination);
    }
}
