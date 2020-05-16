package com.linepro.modellbahn.converter.impl;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.model.SoftDelete;

public class SoftDeleteTranscriber<S extends SoftDelete, D extends SoftDelete> implements Transcriber<S, D> {
    public D apply(S source, D destination, int depth) {
        if (source != null) {
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }
}
