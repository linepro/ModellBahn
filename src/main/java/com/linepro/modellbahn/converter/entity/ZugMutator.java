package com.linepro.modellbahn.converter.entity;

import java.util.stream.Collectors;

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

    @Autowired
    private ZugConsistMutator consistMutator;

    public ZugModel apply(Zug source, ZugModel destination, int depth) {
        destination.setName(source.getName());
        destination.setBezeichnung(source.getBezeichnung());
        destination.setZugTyp(zugTypMutator.convert(source.getZugTyp()));
        
        if (depth > 0) {
            destination.setConsist(source.getConsist()
                                         .stream()
                                         .sorted()
                                         .map(c -> consistMutator.convert(c))
                                         .collect(Collectors.toList()));
        }
        return destination;
    }

    @Override
    public ZugModel get() {
        return new ZugModel();
    }
}
