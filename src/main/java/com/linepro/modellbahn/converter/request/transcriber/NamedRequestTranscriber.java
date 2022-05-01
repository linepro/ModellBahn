package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.linepro.modellbahn.converter.impl.SoftDeleteTranscriber;
import com.linepro.modellbahn.model.Named;
import com.linepro.modellbahn.service.NameGenerator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "NamedRequestTranscriber")
public class NamedRequestTranscriber<S extends Named, D extends Named> extends SoftDeleteTranscriber<S, D> {

    private final NameGenerator generator;

    @Override
    public D apply(S source, D destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            if (destination.getName() == null) {
                destination.setName(StringUtils.hasText(source.getName()) ? source.getName() : generator.generate(source.getBezeichnung())                );
            }
            destination.setBezeichnung(source.getBezeichnung());
            destination.setDeleted(Optional.ofNullable(source.getDeleted()).orElse(Boolean.FALSE));
        }

        return destination;
    }
}
