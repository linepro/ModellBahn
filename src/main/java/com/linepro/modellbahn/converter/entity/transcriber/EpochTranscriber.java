package com.linepro.modellbahn.converter.entity.transcriber;

import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

public class EpochTranscriber extends NamedTranscriber<Epoch, EpochModel> {
    @Override
    public EpochModel apply(Epoch source, EpochModel destination) {
        destination.setStartYear(source.getStartYear());
        destination.setEndYear(source.getEndYear());

        return super.apply(source, destination);
    }
}
