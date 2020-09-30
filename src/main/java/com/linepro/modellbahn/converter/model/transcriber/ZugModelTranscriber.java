package com.linepro.modellbahn.converter.model.transcriber;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;

import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ZugTypRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZugModelTranscriber extends NamedModelTranscriber<ZugModel, Zug> {

    private final ZugTypRepository repository;

    private final ItemLookup lookup;

    @Override
    public Zug apply(ZugModel source, Zug destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setZugTyp(lookup.find(source.getZugTyp(), repository));
        }

        return super.apply(source, destination);
    }

}
