package com.linepro.modellbahn.converter.model.transcriber;

import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.model.EpochModel;

public class EpochModelTranscriber extends NamedModelTranscriber<EpochModel, Epoch> {
    @Override
    public Epoch apply(EpochModel source, Epoch destination) {
        destination.setStartYear(source.getStartYear());
        destination.setEndYear(source.getEndYear());

        return super.apply(source, destination);
    }
}
