package com.linepro.modellbahn.converter.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ZugTypRepository;
import com.linepro.modellbahn.repository.lookup.ItemLookup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ZugModelMutator implements Mutator<ZugModel, Zug> {

    @Autowired
    private final ZugTypRepository repository;

    @Autowired
    private final ItemLookup lookup;

    public Zug apply(ZugModel source, Zug destination, int depth) {
        destination.setBezeichnung(source.getBezeichnung());
        destination.setZugTyp(lookup.find(source.getZugTyp(), repository));
        return destination;
    }

    @Override
    public Zug get() {
        return new Zug();
    }
}
