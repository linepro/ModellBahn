package com.linepro.modellbahn.converter;

public interface Transcriber<S,D> {
    default D apply(S source, D destination) {
        return apply(source, destination, 0);
    }

    D apply(S source, D destination, int depth);
}
