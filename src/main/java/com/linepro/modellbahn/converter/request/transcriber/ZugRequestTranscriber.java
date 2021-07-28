package com.linepro.modellbahn.converter.request.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.repository.ZugTypRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;
import com.linepro.modellbahn.request.ZugRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZugRequestTranscriber extends NamedRequestTranscriber<ZugRequest, Zug> {

    private final ZugTypRepository repository;

    private final ItemLookup lookup;

    @Override
    public Zug apply(ZugRequest source, Zug destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setZugTyp(lookup.find(source.getZugTyp(), repository));
        }

        return super.apply(source, destination);
    }

}
