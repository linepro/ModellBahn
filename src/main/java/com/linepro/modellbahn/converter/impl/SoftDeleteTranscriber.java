package com.linepro.modellbahn.converter.impl;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.model.SoftDelete;

public class SoftDeleteTranscriber<S extends SoftDelete, D extends SoftDelete> implements Transcriber<S, D> {
    @Override
    public D apply(S source, D destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            // You can only delete by calling a DELETE; this is read only
            // destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }
}
