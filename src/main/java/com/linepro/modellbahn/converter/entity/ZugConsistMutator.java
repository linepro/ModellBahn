package com.linepro.modellbahn.converter.entity;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.Mutator;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugConsistModel;

@Component
public class ZugConsistMutator implements Mutator<ZugConsist, ZugConsistModel> {

    @Override
    public ZugConsistModel apply(ZugConsist source, ZugConsistModel destination, int depth) {
        return destination;
    }
    
    @Override
    public ZugConsistModel get() {
        return new ZugConsistModel();
    }
}
