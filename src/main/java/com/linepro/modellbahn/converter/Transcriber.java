package com.linepro.modellbahn.converter;

public interface Transcriber<S,D> {
    default D apply(S source, D destination) {
        return applyFields(source, destination);
    }

    D applyFields(S source, D destination);

    default D applySummary(S source, D destination) {
        return applyFields(source, destination);
    }
}
