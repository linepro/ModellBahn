package com.linepro.modellbahn.converter.entity.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.PathMapper;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

@Component(PREFIX + "EpochTranscriber")
public class EpochTranscriber extends NamedAbbildungTranscriber<Epoch, EpochModel> {

    public EpochTranscriber(PathMapper pathMapper) {
        super(pathMapper);
    }

    @Override
    public EpochModel apply(Epoch source, EpochModel destination) {
        destination.setStartYear(source.getStartYear());
        destination.setEndYear(source.getEndYear());

        return super.apply(source, destination);
    }
}
