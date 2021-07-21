package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import com.linepro.modellbahn.converter.impl.SoftDeleteTranscriber;
import com.linepro.modellbahn.model.Named;

public class NamedRequestTranscriber<S extends Named, D extends Named> extends SoftDeleteTranscriber<S, D> {

    @Override
    public D apply(S source, D destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getName() == null) {
                destination.setName(source.getName());
            }
            destination.setBezeichnung(source.getBezeichnung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
