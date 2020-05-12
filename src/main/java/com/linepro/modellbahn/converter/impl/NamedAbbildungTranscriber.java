package com.linepro.modellbahn.converter.impl;

import com.linepro.modellbahn.util.NamedWithAbbildung;

public class NamedAbbildungTranscriber<S extends NamedWithAbbildung, D extends NamedWithAbbildung> extends NamedTranscriber<S, D> {
    public D apply(S source, D destination) {
        destination = super.apply(source, destination);

        if (source != null) {
            destination.setAbbildung(source.getAbbildung());
        }
        
        return destination;
    }
}
