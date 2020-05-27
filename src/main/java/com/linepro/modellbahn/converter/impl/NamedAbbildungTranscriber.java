package com.linepro.modellbahn.converter.impl;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.model.NamedWithAbbildung;

public class NamedAbbildungTranscriber<S extends NamedWithAbbildung, D extends NamedWithAbbildung> extends NamedTranscriber<S, D> {
    @Override
    public D applyFields(S source, D destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setAbbildung(source.getAbbildung());
        }
        
        return super.applyFields(source, destination);
    }

    @Override
    public D applySummary(S source, D destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setAbbildung(source.getAbbildung());
        }
        
        return super.applySummary(source, destination);
    }
}
