package com.linepro.modellbahn.converter.impl;

import com.linepro.modellbahn.model.NamedWithAbbildung;

public class NamedAbbildungTranscriber<S extends NamedWithAbbildung, D extends NamedWithAbbildung> extends NamedTranscriber<S, D> {
    public D apply(S source, D destination, int depth) {
        destination = super.apply(source, destination, depth);

        if (source != null) {
            destination.setAbbildung(source.getAbbildung());
        }
        
        return destination;
    }
}
