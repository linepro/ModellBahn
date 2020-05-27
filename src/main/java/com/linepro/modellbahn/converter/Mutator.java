package com.linepro.modellbahn.converter;

import java.util.function.Supplier;

import org.springframework.core.convert.converter.Converter;

public interface Mutator<S,D> extends Converter<S,D>, Supplier<D>, Transcriber<S,D> {
    
    default D convert(S model) {
        if (model != null) {
            return apply(model, get());
        }
        
        return null;
    }
    
    default D summarize(S model) {
        if (model != null) {
            return applySummary(model, get());
        }
        
        return null;
    }
}
