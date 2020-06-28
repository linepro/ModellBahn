package com.linepro.modellbahn.converter.model;

import org.springframework.stereotype.Component;

import com.linepro.modellbahn.converter.impl.MutatorImpl;
import com.linepro.modellbahn.converter.impl.NamedTranscriber;
import com.linepro.modellbahn.entity.ZugTyp;
import com.linepro.modellbahn.model.ZugTypModel;

@Component("ZugTypModelMutator")
public class ZugTypModelMutator extends MutatorImpl<ZugTypModel, ZugTyp> {

    public ZugTypModelMutator() {
        super(() -> new ZugTyp(), new NamedTranscriber<>());
    }
}
