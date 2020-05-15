package com.linepro.modellbahn.converter.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ZugMutator implements Mutator<Zug,ZugModel> {
    
    @Autowired
    private ZugTypMutator zugTypMutator;

    public ZugModel apply(Zug source, ZugModel destination, int depth) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setZugTyp(zugTypMutator.convert(source.getZugTyp()));
        return destination;
    }

    @Override
    public ZugModel get() {
        return new ZugModel();
    }
}
