package com.linepro.modellbahn.converter;

import java.util.function.Supplier;

import org.springframework.core.convert.converter.Converter;

public interface Mutator<S,D> extends Converter<S,D>, Supplier<D>, Transcriber<S,D> {
    D apply(S source, D destination);

    @Override
    default D convert(S source) {
        return apply(source, get());
    }
}
