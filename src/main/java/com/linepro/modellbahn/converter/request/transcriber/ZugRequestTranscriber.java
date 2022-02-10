package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.ModellBahnApplication.PREFIX;
import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.repository.lookup.ZugTypLookup;
import com.linepro.modellbahn.request.ZugRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component(PREFIX + "ZugRequestTranscriber")
public class ZugRequestTranscriber extends NamedRequestTranscriber<ZugRequest, Zug> {

    private final ZugTypLookup lookup;

    @Override
    public Zug apply(ZugRequest source, Zug destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            lookup.find(source.getZugTyp()).ifPresent(t-> destination.setZugTyp(t));
        }

        return super.apply(source, destination);
    }

}
