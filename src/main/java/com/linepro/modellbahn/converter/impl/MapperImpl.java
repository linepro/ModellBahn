package com.linepro.modellbahn.converter.impl;

import java.util.function.Supplier;

import com.linepro.modellbahn.converter.Mapper;
import com.linepro.modellbahn.converter.Transcriber;

public class MapperImpl<S,D> implements Mapper<S, D> {

    private final Supplier<D> supplier;
    private final Transcriber<S,D> transcriber;

    public MapperImpl(Supplier<D> supplier, Transcriber<S,D> transcriber) {
        this.supplier = supplier;
        this.transcriber = transcriber;
    }

    @Override
    public D get() {
        return supplier.get();
    }

    @Override
    public D apply(S source, D destination) {
        return transcriber.apply(source, destination);
    }
}
