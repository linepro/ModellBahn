package com.linepro.modellbahn.converter.impl;

import com.linepro.modellbahn.converter.Transcriber;
import com.linepro.modellbahn.util.SoftDelete;

public class SoftDeleteTranscriber<S extends SoftDelete, D extends SoftDelete> implements Transcriber<S, D> {
    public D apply(S source, D destination) {
        if (source != null) {
            destination.setDeleted(source.getDeleted());
        }
        
        return destination;
    }
}
