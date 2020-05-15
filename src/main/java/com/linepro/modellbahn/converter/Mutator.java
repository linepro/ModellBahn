package com.linepro.modellbahn.converter;

import java.util.function.Supplier;

import org.springframework.core.convert.converter.Converter;

public interface Mutator<S,D> extends Converter<S,D>, Supplier<D>, Transcriber<S,D> {
    @Override
    default D convert(S source) {
        return convert(source, 0);
    }

    default D convert(S source, int depth) {
        if (source != null && depth >= 0) {
            return apply(source, get(), --depth);
        }
        
        return null;
    }
}
