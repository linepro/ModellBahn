package com.linepro.modellbahn.converter.request.transcriber;

import com.linepro.modellbahn.entity.Epoch;
import com.linepro.modellbahn.request.EpochRequest;

public class EpochRequestTranscriber extends NamedRequestTranscriber<EpochRequest, Epoch> {

    @Override
    public Epoch apply(EpochRequest source, Epoch destination) {
        destination.setStartYear(source.getStartYear());
        destination.setEndYear(source.getEndYear());

        return super.apply(source, destination);
    }
}
