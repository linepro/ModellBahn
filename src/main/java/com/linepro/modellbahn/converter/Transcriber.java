package com.linepro.modellbahn.converter;

public interface Transcriber<S,D> {
    D apply(S source, D destination);
}
