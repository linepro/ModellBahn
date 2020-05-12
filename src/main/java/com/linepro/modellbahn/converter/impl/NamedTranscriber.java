package com.linepro.modellbahn.converter.impl;

import com.linepro.modellbahn.util.Named;

public class NamedTranscriber<S extends Named, D extends Named> extends SoftDeleteTranscriber<S, D> {

    @Override
    public D apply(S source, D destination) {
        destination = super.apply(source, destination);

        if (source != null) {
            destination.setName(source.getName());
            destination.setBezeichnung(source.getBezeichnung());
        }
        
        return destination;
    }
}
