package com.linepro.modellbahn.converter.impl;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.model.Named;

public class NamedTranscriber<S extends Named, D extends Named> extends SoftDeleteTranscriber<S, D> {

    @Override
    public D applySummary(S source, D destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setName(source.getName());
            destination.setBezeichnung(source.getBezeichnung());
        }
        
        return super.applySummary(source, destination);
    }

    @Override
    public D applyFields(S source, D destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setName(source.getName());
            destination.setBezeichnung(source.getBezeichnung());
        }
        
        return super.applyFields(source, destination);
    }
}
