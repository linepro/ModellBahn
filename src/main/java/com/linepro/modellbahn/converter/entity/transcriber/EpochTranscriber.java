package com.linepro.modellbahn.converter.entity.transcriber;

import com.linepro.modellbahn.converter.PathMutator;
import com.linepro.modellbahn.converter.impl.NamedAbbildungTranscriber;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

public class EpochTranscriber extends NamedAbbildungTranscriber<Epoch, EpochModel> {

    public EpochTranscriber(PathMutator pathMutator) {
        super(pathMutator);
    }

    @Override
    public EpochModel apply(Epoch source, EpochModel destination) {
        destination.setStartYear(source.getStartYear());
        destination.setEndYear(source.getEndYear());

        return super.apply(source, destination);
    }
}
