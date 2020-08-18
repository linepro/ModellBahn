package com.linepro.modellbahn.converter;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.entity.NamedItem;

public interface Transcriber<S,D> {
    D apply(S source, D destination);

    default <M extends NamedItem> String getCode(M item) {
        return item != null && isAvailable(item.getName()) ? item.getName() : null;
    }
}
