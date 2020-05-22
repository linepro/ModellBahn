package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.util.exceptions.Result.attempt;

import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.util.exceptions.ResultCollector;

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
        
        if (depth >= 0) {
            if (source.getConsist() instanceof PersistentSet && ((PersistentSet) source.getConsist()).wasInitialized()) {
                destination.setConsist(source.getConsist()
                                             .stream()
                                             .sorted()
                                             .map(c -> attempt(() -> consistMutator.convert(c)))
                                             .collect(new ResultCollector<>())
                                             .orElseThrow());
            }
        }
        return destination;
    }

    @Override
    public ZugModel get() {
        return new ZugModel();
    }
}
