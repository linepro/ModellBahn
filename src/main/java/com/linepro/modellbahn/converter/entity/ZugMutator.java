package com.linepro.modellbahn.converter.entity;

import static com.linepro.modellbahn.persistence.util.ProxyUtils.isAvailable;
import static com.linepro.modellbahn.util.exceptions.Result.attempt;
import static com.linepro.modellbahn.util.exceptions.ResultCollector.success;

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

    public ZugModel applyFields(Zug source, ZugModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            applySummary(source, destination);
            
            if (isAvailable(source.getConsist())) {
                destination.setConsist(source.getConsist()
                                             .stream()
                                             .sorted()
                                             .map(c -> attempt(() -> consistMutator.convert(c)))
                                             .collect(success())
                                             .orElseThrow());
            }
        }
        
        return destination;
    }

    @Override
    public ZugModel applySummary(Zug source, ZugModel destination) {
        if (isAvailable(source) && isAvailable(destination)) {
            destination.setName(source.getName());
            destination.setBezeichnung(source.getBezeichnung());
            destination.setZugTyp(zugTypMutator.summarize(source.getZugTyp()));
        }
        
        return destination;
    }

    @Override
    public ZugModel get() {
        return new ZugModel();
    }
}
