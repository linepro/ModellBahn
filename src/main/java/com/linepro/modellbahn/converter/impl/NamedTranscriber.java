package com.linepro.modellbahn.converter.impl;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.model.Named;

@Component(PREFIX + "NamedTranscriber")
public class NamedTranscriber<S extends Named, D extends Named> extends SoftDeleteTranscriber<S, D> {

    @Override
    public D apply(S source, D destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setName(source.getName());
            destination.setBezeichnung(source.getBezeichnung());
        }

        return super.apply(source, destination);
    }
}
