package com.linepro.modellbahn.converter;

import java.util.function.Supplier;

import org.springframework.core.convert.converter.Converter;

import com.linepro.modellbahn.entity.NamedItem;

public interface Mutator<S,D> extends Converter<S,D>, Supplier<D>, Transcriber<S,D> {

    default D convert(S model) {
        if (model != null) {
            return apply(model, get());
        }

        return null;
    }

    default <M extends NamedItem> String getCode(M item) {
        return item != null ? item.getName() : null;
    }
}
